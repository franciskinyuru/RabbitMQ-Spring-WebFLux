package queuewebflux.queue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import queuewebflux.queue.dto.OrderDTO;
import queuewebflux.queue.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Sender;

@Service
public class OrderService {
    final Sender sender;

    public OrderService(Sender sender) {
        this.sender = sender;
    }
    private static final String QUEUE = "order-queue";
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public Mono<Order> createOrder(Mono<OrderDTO> dtoMono){
        return dtoMono.flatMap(orderDto->{
            Order order=mapperOrderDTOToEntity(orderDto);
            ObjectMapper mapper=new ObjectMapper();
            String json;
            try {
                json = mapper.writeValueAsString(order);
                byte[] orderSerialized = SerializationUtils.serialize(json);
                Flux<OutboundMessage> outboundMessageFlux = Flux.just(new OutboundMessage(""
                ,QUEUE,orderSerialized));

                sender
                        .declareQueue(QueueSpecification.queue(QUEUE))
                        .thenMany(sender.sendWithPublishConfirms(outboundMessageFlux))
                        .doOnError(e->LOGGER.error("send failed", e))
                        .subscribe(msg ->{
                            System.out.println("message sent");
                        });
            } catch (JsonProcessingException exception) {
                exception.printStackTrace();
            }
            return Mono.just(order);
        });
    }

    public Order mapperOrderDTOToEntity(OrderDTO dto){
        Order order = new Order();
        order.setItems(dto.getItems());
        order.setTotalCost(order.calculateTotalCost());

        return order;
    }
}

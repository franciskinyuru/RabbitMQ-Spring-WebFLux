package rabbitmqconsume.consumefromapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Connection;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmqconsume.consumefromapi.model.Items;
import rabbitmqconsume.consumefromapi.model.Order;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private static final  String QUEUE = "order-queue";

    @Autowired
    private Mono<Connection> connectionMono;
    @Autowired
    AddToDB addToDB;


    final Receiver receiver;


    public OrderService(Receiver receiver) {
        this.receiver = receiver;
    }
    @PostConstruct
    private void init(){
        consume();
    }

    @PreDestroy
    public void close() throws Exception{
        connectionMono.block().close();
    }

    public Disposable consume(){
        return receiver.consumeNoAck(QUEUE).subscribe(msg->{
            String json = SerializationUtils.deserialize(msg.getBody());
            ObjectMapper mapper = new ObjectMapper();
            Order order;
            try{
                order = mapper.readValue(json,Order.class);

                Order order1 = new Order();
                String uuid= UUID.randomUUID().toString();
                order1.setId(uuid);
                order1.setTotalCost(order.getTotalCost());
                addToDB.addToOrders(order1);
                Items items=new Items();
                for (Items item:order.getItems() ){
                    items.setId(uuid);
                    items.setName(item.getName());
                    items.setPrice(item.getPrice());
                    addToDB.addToItems(items);
                }

                System.out.println("consumed all queue" );
            } catch (JsonMappingException e) {
                System.out.println(e.getMessage());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });
    }

}

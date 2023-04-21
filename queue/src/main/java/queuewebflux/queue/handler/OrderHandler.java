package queuewebflux.queue.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import queuewebflux.queue.dto.OrderDTO;
import queuewebflux.queue.model.Order;
import queuewebflux.queue.service.OrderService;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    private final OrderService service;

    public OrderHandler(OrderService service) {
        this.service = service;
    }
    public Mono<ServerResponse> createOrder(ServerRequest request){
        System.out.println(request);
        Mono<OrderDTO> dto=request.bodyToMono(OrderDTO.class);
        Mono<Order> result = service.createOrder(dto);
        return ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, Order.class);
    }

}

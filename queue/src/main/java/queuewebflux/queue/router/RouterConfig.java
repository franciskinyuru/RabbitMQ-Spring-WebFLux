package queuewebflux.queue.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import queuewebflux.queue.handler.OrderHandler;

@Configuration
public class RouterConfig {
//    private static final String ENDPOINT_PATH = "/create-order";
    @Bean
    public RouterFunction<ServerResponse> routerFunction(OrderHandler handler){
        return RouterFunctions.route(RequestPredicates.POST("/create-order"),handler::createOrder);
    }
}

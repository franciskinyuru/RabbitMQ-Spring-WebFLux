package rabbitmqconsume.consumefromapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabbitmqconsume.consumefromapi.model.Items;
import rabbitmqconsume.consumefromapi.model.Order;
import rabbitmqconsume.consumefromapi.repository.ItemsRepository;
import rabbitmqconsume.consumefromapi.repository.OrderRepository;
import reactor.core.publisher.Mono;

@Service
public class AddToDB {

    @Autowired
    ItemsRepository itemsRepository;

    @Autowired
    OrderRepository orderRepository;

    public Mono<Items> addToItems(Items items){
        return itemsRepository.save(items);
    }
    public Mono<Order> addToOrders(Order order){
        return orderRepository.save(order);
    }
}

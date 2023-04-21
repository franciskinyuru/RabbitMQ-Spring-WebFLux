package rabbitmqconsume.consumefromapi.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import rabbitmqconsume.consumefromapi.model.Order;

@Repository
public interface OrderRepository  extends ReactiveCrudRepository<Order, String> {
}

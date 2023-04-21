package rabbitmqconsume.consumefromapi.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import rabbitmqconsume.consumefromapi.model.Items;

@Repository
public interface ItemsRepository extends ReactiveCrudRepository<Items, String> {
}

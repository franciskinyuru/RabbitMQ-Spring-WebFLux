package queuewebflux.queue;

import com.rabbitmq.client.Connection;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableWebFlux
public class QueueApplication {

	@Autowired
	private Mono<Connection> connectionMono;

	public static void main(String[] args) {
		SpringApplication.run(QueueApplication.class, args);
	}


	@PreDestroy
	public void close() throws Exception{
		connectionMono.block().close();
	}
}

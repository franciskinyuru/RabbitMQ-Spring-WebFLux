package rabbitmqconsume.consumefromapi.model;

import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Table("items")
public class Items implements Serializable {
    @Id
    private int unique_id;
    private String id;
    private String name;
    private double price;
}

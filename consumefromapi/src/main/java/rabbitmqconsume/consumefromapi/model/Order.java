package rabbitmqconsume.consumefromapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("orders")
public class Order implements Serializable {
    @Id
    private int refId;
    private String id;
    private Items[] items;
    @Column("totalcost")
    double totalCost;

    public Order(String id, double totalCost) {
        this.id = id;
        this.totalCost = totalCost;
    }
}

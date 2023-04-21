package queuewebflux.queue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
    private String id;
    private Item[] items;
    private double totalCost;

    public Order(Item[] items) {
        this.items = items;
        this.totalCost=this.calculateTotalCost();
    }
    public double calculateTotalCost(){
        double result=0;
        List<Item> itemsList= Arrays.asList(items);
        for (Item item: itemsList){
            result +=item.getPrice();
        }
        return  result;

    }
}

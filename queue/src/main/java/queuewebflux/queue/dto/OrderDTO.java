package queuewebflux.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import queuewebflux.queue.model.Item;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;
    private Item[] items;
}

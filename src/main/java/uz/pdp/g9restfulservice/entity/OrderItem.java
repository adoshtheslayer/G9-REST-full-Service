package uz.pdp.g9restfulservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private short quantity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order order;

}

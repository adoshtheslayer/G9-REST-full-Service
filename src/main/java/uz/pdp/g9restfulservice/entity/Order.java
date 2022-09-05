package uz.pdp.g9restfulservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Double total_price;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private  User user;
    private LocalDateTime created_at;


}

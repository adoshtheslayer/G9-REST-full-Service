package uz.pdp.g9restfulservice.entity;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    private String paymentType;

    private LocalDate payDate;

//    @OneToOne
//    private Order order;

}

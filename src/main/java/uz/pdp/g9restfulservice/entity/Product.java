package uz.pdp.g9restfulservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer quantity;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Discount discount;

    @OneToOne
    private Attachment attachment;

    @ManyToMany
    private List<Characteristic> characteristics;

}

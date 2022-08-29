package uz.pdp.g9restfulservice.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent_id;
}

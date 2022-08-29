package uz.pdp.g9restfulservice.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;
}

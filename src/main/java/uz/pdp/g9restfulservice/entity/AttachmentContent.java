package uz.pdp.g9restfulservice.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class AttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private byte[] bytes;

    @OneToOne
    private Attachment attachment;
}

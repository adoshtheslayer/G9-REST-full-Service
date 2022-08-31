package uz.pdp.g9restfulservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public String toString() {
        return "AttachmentContent{" +
                "id=" + id +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}

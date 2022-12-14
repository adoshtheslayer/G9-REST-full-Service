package uz.pdp.g9restfulservice.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;

    @OneToOne(mappedBy = "attachment", cascade = CascadeType.ALL)
    private AttachmentContent attachmentContent;

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                '}';
    }
}

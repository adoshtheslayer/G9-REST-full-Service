package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Attachment;
import uz.pdp.g9restfulservice.entity.AttachmentContent;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
}

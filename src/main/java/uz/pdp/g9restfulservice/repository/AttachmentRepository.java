package uz.pdp.g9restfulservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.g9restfulservice.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}

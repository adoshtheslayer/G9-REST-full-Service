package uz.pdp.g9restfulservice.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.g9restfulservice.entity.Attachment;
import uz.pdp.g9restfulservice.entity.AttachmentContent;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.AttachmentContentRepository;
import uz.pdp.g9restfulservice.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    public ApiResponse getAttachment(Long id, HttpServletResponse response) throws IOException {

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();

            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findById(id);
            if (optionalAttachment.isPresent()) {
                AttachmentContent attachmentContent = optionalAttachmentContent.get();

                response.setHeader("Content-Disposition",
                        "attachment; filename=\"" + attachment.getFileName() + "\"");
                response.setContentType(attachment.getContentType());

                FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
                return new ApiResponse("success", true);
            }
        }
        return new ApiResponse("exception in file downloading", false);
    }


    public Attachment saveAttachment(MultipartFile file) throws IOException {

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();

            Attachment attachment = new Attachment();
            attachment.setFileName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return savedAttachment;
        }
        return null;
    }
}

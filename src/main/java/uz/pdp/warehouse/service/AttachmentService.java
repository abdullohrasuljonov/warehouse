package uz.pdp.warehouse.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.entity.Attachment;
import uz.pdp.warehouse.entity.AttachmentContent;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.AttachmentContentRepository;
import uz.pdp.warehouse.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result addAttachment(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        if (file == null)
            return new Result("File not uploaded!", false);
        attachment.setOriginalFileName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File successfully uploaded!", true);
    }

    @SneakyThrows
    public Result getAttachmentById(Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Attachment not found!", false);
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(attachment.getId());
        if (!contentOptional.isPresent())
            return new Result("Attachment Content not found!", false);
        AttachmentContent attachmentContent = contentOptional.get();
        response.setHeader("Content-Disposition", "" +
                "attachment;filename=\"" + attachment.getOriginalFileName() + "\"");
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        return new Result("File succesfully downloaded!", true);
    }

    @SneakyThrows
    public Result editAttachment(Integer id, MultipartHttpServletRequest request) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("Attachment not found!", false);
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = optionalAttachment.get();
        if (file == null)
            return new Result("File not found!", false);
        attachment.setOriginalFileName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(attachment.getId());
        if (!contentOptional.isPresent())
            return new Result("This file not content!",false);
        AttachmentContent attachmentContent = contentOptional.get();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("Successfully edited!",true,attachment.getId());
    }

    public Result deleteAttachment(Integer id){
        Optional<Attachment> byId = attachmentRepository.findById(id);
        if (byId.isPresent()){
            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            byAttachmentId.ifPresent(attachmentContent -> attachmentContentRepository.deleteById(attachmentContent.getId()));
            attachmentRepository.deleteById(id);
            return new Result("Successfully deleted!",true);
        }
        return new Result("There is no file such an id",false);
    }
}







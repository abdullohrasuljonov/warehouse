package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result addAttachment(MultipartHttpServletRequest request){
        return attachmentService.addAttachment(request);
    }

    @GetMapping("download/{id}")
    public Result getAttachment(@PathVariable Integer id, HttpServletResponse response){
        return attachmentService.getAttachmentById(id,response);
    }

    @PutMapping("/{id}")
    public Result editAttachment(@PathVariable Integer id,MultipartHttpServletRequest request){
        return attachmentService.editAttachment(id,request);
    }

    @DeleteMapping("/{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        return attachmentService.deleteAttachment(id);
    }
}















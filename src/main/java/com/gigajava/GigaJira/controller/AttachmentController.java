package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.entity.Attachment;
import com.gigajava.GigaJira.service.AttachmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/attachments")
@CrossOrigin
public class AttachmentController {

    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    public Attachment upload(@RequestParam Long taskId,
                             @RequestParam MultipartFile file,
                             @RequestHeader("X-USER-ID") Long userId) {

        return attachmentService.upload(taskId, userId, file);
    }

    @GetMapping("/task/{taskId}")
    public List<Attachment> getByTask(@PathVariable Long taskId,
                                      @RequestHeader("X-USER-ID") Long userId) {

        return attachmentService.getByTask(taskId, userId);
    }
}
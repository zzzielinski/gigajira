package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.entity.Attachment;
import com.gigajava.GigaJira.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final TaskService taskService;
    private final FileStorageService fileStorageService;

    public AttachmentService(AttachmentRepository attachmentRepository,
                             TaskService taskService,
                             FileStorageService fileStorageService) {
        this.attachmentRepository = attachmentRepository;
        this.taskService = taskService;
        this.fileStorageService = fileStorageService;
    }

    public Attachment upload(Long taskId,
                             Long userId,
                             MultipartFile file) {

        taskService.get(taskId, userId);

        String path = fileStorageService.saveFile(taskId, file);

        Attachment attachment = new Attachment();
        attachment.setTaskId(taskId);
        attachment.setUploadedBy(userId);
        attachment.setFilePath(path);

        return attachmentRepository.save(attachment);
    }

    public List<Attachment> getByTask(Long taskId, Long userId) {

        taskService.get(taskId, userId);

        return attachmentRepository.findByTaskId(taskId);
    }
}
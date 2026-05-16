package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.entity.Attachment;
import com.gigajava.GigaJira.repository.AttachmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final TaskService taskService;

    public AttachmentService(AttachmentRepository attachmentRepository,
                             TaskService taskService) {
        this.attachmentRepository = attachmentRepository;
        this.taskService = taskService;
    }

    public Attachment upload(Attachment attachment, Long userId) {

        taskService.get(attachment.getTaskId(), userId);

        attachment.setUploadedBy(userId);

        return attachmentRepository.save(attachment);
    }

    public List<Attachment> getByTask(Long taskId, Long userId) {

        taskService.get(taskId, userId);

        return attachmentRepository.findByTaskId(taskId);
    }
}
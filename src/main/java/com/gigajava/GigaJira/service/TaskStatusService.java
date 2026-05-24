package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.TaskStatusRequest;
import com.gigajava.GigaJira.entity.TaskStatus;
import com.gigajava.GigaJira.repository.TaskStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;
    private final PermissionService permissionService;

    public TaskStatusService(TaskStatusRepository taskStatusRepository,
                             PermissionService permissionService) {
        this.taskStatusRepository = taskStatusRepository;
        this.permissionService = permissionService;
    }

    public TaskStatus create(TaskStatusRequest request, Long userId) {
        requireAdmin(userId);

        TaskStatus status = new TaskStatus();
        status.setName(request.getName());
        return taskStatusRepository.save(status);
    }

    public List<TaskStatus> getAll() {
        return taskStatusRepository.findAll();
    }

    public TaskStatus update(Long id, TaskStatusRequest request, Long userId) {
        requireAdmin(userId);

        TaskStatus status = taskStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));

        status.setName(request.getName());
        return taskStatusRepository.save(status);
    }

    public void delete(Long id, Long userId) {
        requireAdmin(userId);

        if (!taskStatusRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found");
        }

        taskStatusRepository.deleteById(id);
    }

    private void requireAdmin(Long userId) {
        if (!permissionService.isAdmin(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        }
    }
}

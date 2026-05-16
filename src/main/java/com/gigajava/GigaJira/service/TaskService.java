package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.entity.Task;
import com.gigajava.GigaJira.repository.TaskRepository;
import com.gigajava.GigaJira.repository.ProjectRepository;
import com.gigajava.GigaJira.repository.ProjectMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final PermissionService permissionService;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       ProjectMemberRepository projectMemberRepository,
                       PermissionService permissionService) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.permissionService = permissionService;
    }

    public Task create(Task task, Long userId) {

        if (!permissionService.canAccessProject(userId, task.getProjectId())) {
            throw new RuntimeException("Access denied");
        }

        projectRepository.findById(task.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setCreatedBy(userId);

        return taskRepository.save(task);
    }

    public Task get(Long taskId, Long userId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!permissionService.canAccessProject(userId, task.getProjectId())) {
            throw new RuntimeException("Access denied");
        }

        return task;
    }

    public List<Task> getByProject(Long projectId, Long userId) {

        if (!permissionService.canAccessProject(userId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        return taskRepository.findByProjectId(projectId);
    }

    public Task assign(Long taskId, Long userId, Long assignedUserId) {

        Task task = get(taskId, userId);

        task.setAssignedTo(assignedUserId);

        return taskRepository.save(task);
    }

    public Task changeStatus(Long taskId, Long userId, Long statusId) {

        Task task = get(taskId, userId);

        task.setStatusId(statusId);

        return taskRepository.save(task);
    }
}
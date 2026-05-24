package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.TaskCreateRequest;
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

    public Task create(TaskCreateRequest request, Long userId) {

        if (!permissionService.canAccessProject(userId, request.getProjectId())) {
            throw new RuntimeException("Access denied");
        }

        projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProjectId(request.getProjectId());
        task.setAssignedTo(request.getAssignedTo());
        task.setStatusId(request.getStatusId());
        task.setPriorityId(request.getPriorityId());
        task.setDueDate(request.getDueDate());
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

    public List<Task> filterTasks(Long projectId,
                                  Long statusId,
                                  Long assignedTo,
                                  Long priorityId,
                                  Long userId) {

        if (!permissionService.canAccessProject(userId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        if (statusId != null) {
            return taskRepository.findByProjectIdAndStatusId(projectId, statusId);
        }

        if (assignedTo != null) {
            return taskRepository.findByProjectIdAndAssignedTo(projectId, assignedTo);
        }

        if (priorityId != null) {
            return taskRepository.findByProjectIdAndPriorityId(projectId, priorityId);
        }

        return taskRepository.findByProjectId(projectId);
    }
}
package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.TaskAssignRequest;
import com.gigajava.GigaJira.dto.TaskCreateRequest;
import com.gigajava.GigaJira.dto.TaskStatusUpdateRequest;
import com.gigajava.GigaJira.entity.Task;
import com.gigajava.GigaJira.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@RequestBody TaskCreateRequest request,
                       @RequestHeader("X-USER-ID") Long userId) {

        return taskService.create(request, userId);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable Long id,
                    @RequestHeader("X-USER-ID") Long userId) {

        return taskService.get(id, userId);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getByProject(@PathVariable Long projectId,
                                   @RequestHeader("X-USER-ID") Long userId) {

        return taskService.getByProject(projectId, userId);
    }

    @PutMapping("/{id}/assign")
    public Task assign(@PathVariable Long id,
                       @RequestBody TaskAssignRequest body,
                       @RequestHeader("X-USER-ID") Long userId) {

        return taskService.assign(id, userId, body.getAssignedTo());
    }

    @PutMapping("/{id}/status")
    public Task changeStatus(@PathVariable Long id,
                             @RequestBody TaskStatusUpdateRequest body,
                             @RequestHeader("X-USER-ID") Long userId) {

        return taskService.changeStatus(id, userId, body.getStatusId());
    }

    @GetMapping("/filter")
    public List<Task> filterTasks(
            @RequestParam Long projectId,
            @RequestParam(required = false) Long statusId,
            @RequestParam(required = false) Long assignedTo,
            @RequestParam(required = false) Long priorityId,
            @RequestHeader("X-USER-ID") Long userId
    ) {
        return taskService.filterTasks(projectId, statusId, assignedTo, priorityId, userId);
    }
}
package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.TaskStatusRequest;
import com.gigajava.GigaJira.entity.TaskStatus;
import com.gigajava.GigaJira.service.TaskStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
@CrossOrigin
public class TaskStatusController {

    private final TaskStatusService taskStatusService;

    public TaskStatusController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @PostMapping
    public TaskStatus create(@RequestBody TaskStatusRequest request,
                             @RequestHeader("X-USER-ID") Long userId) {

        return taskStatusService.create(request, userId);
    }

    @GetMapping
    public List<TaskStatus> getAll() {

        return taskStatusService.getAll();
    }

    @PutMapping("/{id}")
    public TaskStatus update(@PathVariable Long id,
                             @RequestBody TaskStatusRequest request,
                             @RequestHeader("X-USER-ID") Long userId) {

        return taskStatusService.update(id, request, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestHeader("X-USER-ID") Long userId) {

        taskStatusService.delete(id, userId);
    }
}

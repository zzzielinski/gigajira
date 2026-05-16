package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.entity.Project;
import com.gigajava.GigaJira.entity.ProjectMember;
import com.gigajava.GigaJira.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Project create(@RequestBody Project project,
                          @RequestHeader("X-USER-ID") Long userId) {

        return projectService.create(project, userId);
    }

    @GetMapping
    public List<Project> getAll(
            @RequestHeader("X-USER-ID") Long userId) {

        return projectService.getAll(userId);
    }

    @GetMapping("/{id}")
    public Project get(@PathVariable Long id,
                       @RequestHeader("X-USER-ID") Long userId) {

        return projectService.get(id, userId);
    }

    @PutMapping("/{id}")
    public Project rename(@PathVariable Long id,
                          @RequestBody Map<String, String> body,
                          @RequestHeader("X-USER-ID") Long userId) {

        return projectService.rename(id, body.get("name"), userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestHeader("X-USER-ID") Long userId) {

        projectService.delete(id, userId);
    }

    @PostMapping("/{id}/members")
    public void addMember(@PathVariable Long id,
                          @RequestBody Map<String, Long> body,
                          @RequestHeader("X-USER-ID") Long userId) {

        projectService.addMember(
                id,
                body.get("userId"),
                userId
        );
    }

    @DeleteMapping("/{id}/members/{memberId}")
    public void removeMember(@PathVariable Long id,
                             @PathVariable Long memberId,
                             @RequestHeader("X-USER-ID") Long userId) {

        projectService.removeMember(id, memberId, userId);
    }

    @GetMapping("/{id}/members")
    public List<ProjectMember> getMembers(
            @PathVariable Long id,
            @RequestHeader("X-USER-ID") Long userId) {

        return projectService.getMembers(id, userId);
    }
}

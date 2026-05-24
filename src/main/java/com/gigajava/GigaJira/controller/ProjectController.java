package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.ProjectCreateRequest;
import com.gigajava.GigaJira.dto.ProjectMemberAddRequest;
import com.gigajava.GigaJira.dto.ProjectRenameRequest;
import com.gigajava.GigaJira.entity.Project;
import com.gigajava.GigaJira.entity.ProjectMember;
import com.gigajava.GigaJira.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Project create(@RequestBody ProjectCreateRequest request,
                          @RequestHeader("X-USER-ID") Long userId) {

        return projectService.create(request, userId);
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
                          @RequestBody ProjectRenameRequest body,
                          @RequestHeader("X-USER-ID") Long userId) {

        return projectService.rename(id, body.getName(), userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestHeader("X-USER-ID") Long userId) {

        projectService.delete(id, userId);
    }

    @PostMapping("/{id}/members")
    public void addMember(@PathVariable Long id,
                          @RequestBody ProjectMemberAddRequest body,
                          @RequestHeader("X-USER-ID") Long userId) {

        projectService.addMember(
                id,
                body.getUserId(),
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

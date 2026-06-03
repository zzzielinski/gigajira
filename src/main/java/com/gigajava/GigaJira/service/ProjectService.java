package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.ProjectCreateRequest;
import com.gigajava.GigaJira.entity.Project;
import com.gigajava.GigaJira.entity.ProjectMember;
import com.gigajava.GigaJira.repository.DomainRepository;
import com.gigajava.GigaJira.repository.ProjectMemberRepository;
import com.gigajava.GigaJira.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final DomainRepository domainRepository;
    private final PermissionService permissionService;

    public ProjectService(ProjectRepository projectRepository,
                          ProjectMemberRepository projectMemberRepository,
                          DomainRepository domainRepository,
                          PermissionService permissionService) {
        this.projectRepository = projectRepository;
        this.projectMemberRepository = projectMemberRepository;
        this.domainRepository = domainRepository;
        this.permissionService = permissionService;
    }

    public Project create(ProjectCreateRequest request, Long userId) {

        domainRepository.findById(request.getDomainId())
                .orElseThrow(() -> new RuntimeException("Domain not found"));

        Project project = new Project();
        project.setName(request.getName());
        project.setDomainId(request.getDomainId());
        project.setCreatedBy(userId);

        Project savedProject = projectRepository.save(project);

        ProjectMember member = new ProjectMember();
        member.setProjectId(savedProject.getId());
        member.setUserId(userId);

        projectMemberRepository.save(member);

        return savedProject;
    }

    public Project get(Long projectId, Long userId) {

        if (!permissionService.canAccessProject(userId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public List<Project> getAll(Long userId) {

        if (permissionService.isAdmin(userId)) {
            return projectRepository.findAll();
        }

        List<ProjectMember> memberships =
                projectMemberRepository.findByUserId(userId);

        List<Project> projects = new ArrayList<>();

        for (ProjectMember member : memberships) {
            projectRepository.findById(member.getProjectId())
                    .ifPresent(projects::add);
        }

        return projects;
    }

    public Project rename(Long projectId, String name, Long userId) {

        if (!permissionService.canAccessProject(userId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(name);

        return projectRepository.save(project);
    }

    public void delete(Long projectId, Long userId) {

        if (!permissionService.isAdmin(userId)) {
            throw new RuntimeException("Only admin can delete projects");
        }

        projectRepository.deleteById(projectId);
    }

    public void addMember(Long projectId, Long memberUserId, Long requesterId) {

        if (!permissionService.canAccessProject(requesterId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        boolean exists = projectMemberRepository
                .existsByProjectIdAndUserId(projectId, memberUserId);

        if (exists) {
            throw new RuntimeException("User already in project");
        }

        ProjectMember member = new ProjectMember();
        member.setProjectId(projectId);
        member.setUserId(memberUserId);

        projectMemberRepository.save(member);
    }

    public void removeMember(Long projectId, Long memberUserId, Long requesterId) {

        if (!permissionService.canAccessProject(requesterId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        ProjectMember member = projectMemberRepository
                .findByProjectIdAndUserId(projectId, memberUserId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        projectMemberRepository.delete(member);
    }

    public List<ProjectMember> getMembers(Long projectId, Long requesterId) {

        if (!permissionService.canAccessProject(requesterId, projectId)) {
            throw new RuntimeException("Access denied");
        }

        return projectMemberRepository.findByProjectId(projectId);
    }
}

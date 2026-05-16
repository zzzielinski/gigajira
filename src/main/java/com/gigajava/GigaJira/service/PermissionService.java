package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.entity.User;
import com.gigajava.GigaJira.repository.ProjectMemberRepository;
import com.gigajava.GigaJira.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public PermissionService(UserRepository userRepository,
                             ProjectMemberRepository projectMemberRepository) {
        this.userRepository = userRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean isAdmin(Long userId) {
        User user = getUser(userId);

        return user.getRole().getName() == "Admin";
    }

    public boolean canAccessProject(Long userId, Long projectId) {

        if (isAdmin(userId)) {
            return true;
        }

        return projectMemberRepository.existsByProjectIdAndUserId(projectId, userId);
    }
}
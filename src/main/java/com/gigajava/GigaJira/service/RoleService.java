package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.RoleRequest;
import com.gigajava.GigaJira.entity.Role;
import com.gigajava.GigaJira.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService;

    public RoleService(RoleRepository roleRepository,
                       PermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    public Role create(RoleRequest request, Long userId) {
        requireAdmin(userId);

        Role role = new Role();
        role.setId(request.getId());
        role.setName(request.getName());
        return roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role update(Long id, RoleRequest request, Long userId) {
        requireAdmin(userId);

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));

        role.setName(request.getName());
        return roleRepository.save(role);
    }

    public void delete(Long id, Long userId) {
        requireAdmin(userId);

        if (!roleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found");
        }

        roleRepository.deleteById(id);
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    private void requireAdmin(Long userId) {
        if (!permissionService.isAdmin(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        }
    }
}
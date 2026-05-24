package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.PriorityRequest;
import com.gigajava.GigaJira.entity.Priority;
import com.gigajava.GigaJira.repository.PriorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;
    private final PermissionService permissionService;

    public PriorityService(PriorityRepository priorityRepository,
                           PermissionService permissionService) {
        this.priorityRepository = priorityRepository;
        this.permissionService = permissionService;
    }

    public Priority create(PriorityRequest request, Long userId) {
        requireAdmin(userId);

        Priority priority = new Priority();
        priority.setLevel(request.getLevel());
        priority.setLabel(request.getLabel());
        return priorityRepository.save(priority);
    }

    public List<Priority> getAll() {
        return priorityRepository.findAll();
    }

    public Priority update(Long id, PriorityRequest request, Long userId) {
        requireAdmin(userId);

        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority not found"));

        priority.setLevel(request.getLevel());
        priority.setLabel(request.getLabel());
        return priorityRepository.save(priority);
    }

    public void delete(Long id, Long userId) {
        requireAdmin(userId);

        if (!priorityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority not found");
        }

        priorityRepository.deleteById(id);
    }

    private void requireAdmin(Long userId) {
        if (!permissionService.isAdmin(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin access required");
        }
    }
}

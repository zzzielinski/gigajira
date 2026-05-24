package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.RoleRequest;
import com.gigajava.GigaJira.entity.Role;
import com.gigajava.GigaJira.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role create(@RequestBody RoleRequest request,
                       @RequestHeader("X-USER-ID") Long userId) {

        return roleService.create(request, userId);
    }

    @GetMapping
    public List<Role> getAll() {

        return roleService.getAll();
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id,
                       @RequestBody RoleRequest request,
                       @RequestHeader("X-USER-ID") Long userId) {

        return roleService.update(id, request, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestHeader("X-USER-ID") Long userId) {

        roleService.delete(id, userId);
    }
}

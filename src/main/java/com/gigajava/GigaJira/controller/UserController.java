package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.LoginRequest;
import com.gigajava.GigaJira.dto.UserCreateRequest;
import com.gigajava.GigaJira.entity.User;
import com.gigajava.GigaJira.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestBody UserCreateRequest request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping
    public java.util.List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/login")
    public Map<String, Long> login(@RequestBody LoginRequest body) {
        User user = userService.login(body.getEmail(), body.getPassword());
        return Map.of("userId", user.getId());
    }
}
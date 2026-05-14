package com.gigajava.GigaJira.controller;

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
    public User create(@RequestBody User user) {
        return userService.create(user);
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
    public Map<String, Long> login(@RequestBody Map<String, String> body) {
        User user = userService.login(body.get("email"), body.get("password"));
        return Map.of("userId", user.getId());
    }
}
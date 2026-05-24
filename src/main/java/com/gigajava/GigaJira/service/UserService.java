package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.UserCreateRequest;
import com.gigajava.GigaJira.entity.Role;
import com.gigajava.GigaJira.entity.User;
import com.gigajava.GigaJira.repository.RoleRepository;
import com.gigajava.GigaJira.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User create(UserCreateRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found: " + request.getRoleId()));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(role);
        user.setDomainId(request.getDomainId());

        return userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}

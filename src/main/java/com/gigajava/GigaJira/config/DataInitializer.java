package com.gigajava.GigaJira.config;

import com.gigajava.GigaJira.entity.Priority;
import com.gigajava.GigaJira.entity.Role;
import com.gigajava.GigaJira.entity.TaskStatus;
import com.gigajava.GigaJira.repository.PriorityRepository;
import com.gigajava.GigaJira.repository.RoleRepository;
import com.gigajava.GigaJira.repository.TaskStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final PriorityRepository priorityRepository;

    public DataInitializer(RoleRepository roleRepository,
                           TaskStatusRepository taskStatusRepository,
                           PriorityRepository priorityRepository) {
        this.roleRepository = roleRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void run(String... args) {

        if (roleRepository.count() == 0) {
            roleRepository.save(role(1L, "Admin"));
            roleRepository.save(role(2L, "PM"));
            roleRepository.save(role(3L, "Dev"));
            System.out.println("Roles initialized");
        }

        if (taskStatusRepository.count() == 0) {
            taskStatusRepository.save(status("Backlog"));
            taskStatusRepository.save(status("To Do"));
            taskStatusRepository.save(status("In Progress"));
            taskStatusRepository.save(status("In Review"));
            taskStatusRepository.save(status("Done"));
            System.out.println("Task statuses initialized");
        }

        if (priorityRepository.count() == 0) {
            priorityRepository.save(priority(1, "Critical"));
            priorityRepository.save(priority(2, "High"));
            priorityRepository.save(priority(3, "Medium"));
            priorityRepository.save(priority(4, "Low"));
            priorityRepository.save(priority(5, "Trivial"));
            System.out.println("Priorities initialized");
        }
    }

    private Role role(Long id, String name) {
        Role r = new Role();
        r.setId(id);
        r.setName(name);
        return r;
    }

    private TaskStatus status(String name) {
        TaskStatus s = new TaskStatus();
        s.setName(name);
        return s;
    }

    private Priority priority(int level, String label) {
        Priority p = new Priority();
        p.setLevel(level);
        p.setLabel(label);
        return p;
    }
}
package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.PriorityRequest;
import com.gigajava.GigaJira.entity.Priority;
import com.gigajava.GigaJira.service.PriorityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priorities")
@CrossOrigin
public class PriorityController {

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostMapping
    public Priority create(@RequestBody PriorityRequest request,
                           @RequestHeader("X-USER-ID") Long userId) {

        return priorityService.create(request, userId);
    }

    @GetMapping
    public List<Priority> getAll() {

        return priorityService.getAll();
    }

    @PutMapping("/{id}")
    public Priority update(@PathVariable Long id,
                           @RequestBody PriorityRequest request,
                           @RequestHeader("X-USER-ID") Long userId) {

        return priorityService.update(id, request, userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestHeader("X-USER-ID") Long userId) {

        priorityService.delete(id, userId);
    }
}

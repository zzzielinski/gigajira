package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.entity.Domain;
import com.gigajava.GigaJira.service.DomainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping
    public Domain create(@RequestBody Domain domain) {
        return domainService.create(domain);
    }

    @GetMapping
    public List<Domain> getAll() {
        return domainService.getAll();
    }

    @GetMapping("/{id}")
    public Domain get(@PathVariable Long id) {
        return domainService.get(id);
    }
}
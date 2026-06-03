package com.gigajava.GigaJira.controller;

import com.gigajava.GigaJira.dto.DomainCreateRequest;
import com.gigajava.GigaJira.entity.Domain;
import com.gigajava.GigaJira.service.DomainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domains")
@CrossOrigin
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @PostMapping
    public Domain create(@RequestBody DomainCreateRequest request) {
        return domainService.create(request);
    }

    @GetMapping
    public List<Domain> getAll() {
        return domainService.getAll();
    }

    @GetMapping("/company/{companyId}")
    public List<Domain> getByCompany(@PathVariable Long companyId) {
        return domainService.getByCompany(companyId);
    }

    @GetMapping("/{id}")
    public Domain get(@PathVariable Long id) {
        return domainService.get(id);
    }
}

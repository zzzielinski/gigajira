package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.entity.Domain;
import com.gigajava.GigaJira.repository.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public Domain create(Domain domain) {
        return domainRepository.save(domain);
    }

    public List<Domain> getAll() {
        return domainRepository.findAll();
    }

    public Domain get(Long id) {
        return domainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domain not found"));
    }
}
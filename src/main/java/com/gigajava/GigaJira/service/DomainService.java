package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.DomainCreateRequest;
import com.gigajava.GigaJira.entity.Domain;
import com.gigajava.GigaJira.repository.CompanyRepository;
import com.gigajava.GigaJira.repository.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    private final DomainRepository domainRepository;
    private final CompanyRepository companyRepository;

    public DomainService(DomainRepository domainRepository,
                         CompanyRepository companyRepository) {
        this.domainRepository = domainRepository;
        this.companyRepository = companyRepository;
    }

    public Domain create(DomainCreateRequest request) {
        companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Domain domain = new Domain();
        domain.setName(request.getName());
        domain.setCompanyId(request.getCompanyId());
        return domainRepository.save(domain);
    }

    public List<Domain> getAll() {
        return domainRepository.findAll();
    }

    public List<Domain> getByCompany(Long companyId) {
        return domainRepository.findByCompanyId(companyId);
    }

    public Domain get(Long id) {
        return domainRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domain not found"));
    }
}

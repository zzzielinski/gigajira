package com.gigajava.GigaJira.service;

import com.gigajava.GigaJira.dto.CompanyCreateRequest;
import com.gigajava.GigaJira.entity.Company;
import com.gigajava.GigaJira.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company create(CompanyCreateRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setDomainId(request.getDomainId());
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company get(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }
}
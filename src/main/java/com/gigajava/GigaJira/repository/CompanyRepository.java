package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
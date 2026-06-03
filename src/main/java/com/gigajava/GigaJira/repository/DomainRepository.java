package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    List<Domain> findByCompanyId(Long companyId);
}

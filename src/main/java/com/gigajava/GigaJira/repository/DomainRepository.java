package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain, Long> {
}
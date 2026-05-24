package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}

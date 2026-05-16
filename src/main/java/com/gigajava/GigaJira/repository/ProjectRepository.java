package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
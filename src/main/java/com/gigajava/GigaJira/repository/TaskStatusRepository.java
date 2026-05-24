package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}

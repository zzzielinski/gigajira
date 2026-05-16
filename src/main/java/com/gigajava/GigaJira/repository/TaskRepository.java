package com.gigajava.GigaJira.repository;

import com.gigajava.GigaJira.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    List<Task> findByProjectIdAndStatusId(Long projectId, Long statusId);

    List<Task> findByProjectIdAndAssignedTo(Long projectId, Long userId);

    List<Task> findByProjectIdAndPriorityId(Long projectId, Long priorityId);
}
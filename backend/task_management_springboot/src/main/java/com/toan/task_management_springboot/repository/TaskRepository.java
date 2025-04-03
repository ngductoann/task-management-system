package com.toan.task_management_springboot.repository;

import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;
import com.toan.task_management_springboot.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);

    List<Task> findByStatusAndPriority(Status status, Priority priority);

    List<Task> findByTitleContaining(String title);
}

package com.dteam.springboottasks.repository;

import com.dteam.springboottasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findAllByOrderByCreatedAtDesc();

    List<Task> findByIdContainingOrTitleContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(String id, String title, String description);
}

package com.dteam.springboottasks.service;

import com.dteam.springboottasks.model.Task;
import com.dteam.springboottasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {return taskRepository.findAllByOrderByCreatedAtDesc();}


    @Override
    public List<Task> getTasksContainingText(String text) {
        return taskRepository.findByIdContainingOrTitleContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(text, text, text);
    }

    @Override
    public Task validateAndGetTask(String id) {
        return null;
    }

    @Override
    public Task saveTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Task task) {

    }
}

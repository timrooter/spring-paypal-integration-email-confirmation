package com.dteam.springboottasks.service;

import com.dteam.springboottasks.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    List<Task> getTasksContainingText(String text);

    Task validateAndGetTask(String id);

    Task saveTask(Task task);

    void deleteTask(Task task);
}

package com.dteam.springboottasks.mapper;

import com.dteam.springboottasks.model.Task;
import com.dteam.springboottasks.restapi.dto.CreateTaskRequest;
import com.dteam.springboottasks.restapi.dto.TaskDto;

public interface TaskMapper {

    Task toTask(CreateTaskRequest createTaskRequest);

    TaskDto toTaskDto(Task task);
}

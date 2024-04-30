package com.dteam.springboottasks.mapper;

import com.dteam.springboottasks.model.Task;
import com.dteam.springboottasks.restapi.dto.CreateTaskRequest;
import com.dteam.springboottasks.restapi.dto.TaskDto;
import org.springframework.stereotype.Service;

@Service
public class TaskMapperImpl implements TaskMapper{
    @Override
    public Task toTask(CreateTaskRequest createTaskRequest) {
        if (createTaskRequest == null){
            return null;
        }
        return new Task(createTaskRequest.getTitle(), createTaskRequest.getDescription());
    }

    @Override
    public TaskDto toTaskDto(Task task) {
        return null;
    }
}

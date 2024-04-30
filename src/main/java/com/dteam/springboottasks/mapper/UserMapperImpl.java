package com.dteam.springboottasks.mapper;

import com.dteam.springboottasks.model.Task;
import com.dteam.springboottasks.model.User;
import com.dteam.springboottasks.restapi.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        List<UserDto.TaskDto> tasks = user.getTasks().stream().map(this::toUserDtoTaskDto).toList();
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getRole(), tasks);
    }

    private UserDto.TaskDto toUserDtoTaskDto(Task task) {
        if (task == null) {
            return null;
        }
        return new UserDto.TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getCreatedAt());
    }
}

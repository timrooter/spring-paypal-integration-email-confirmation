package com.dteam.springboottasks.restapi;

import com.dteam.springboottasks.mapper.TaskMapper;
import com.dteam.springboottasks.model.Task;
import com.dteam.springboottasks.model.User;
import com.dteam.springboottasks.restapi.dto.CreateTaskRequest;
import com.dteam.springboottasks.restapi.dto.TaskDto;
import com.dteam.springboottasks.security.CustomUserDetails;
import com.dteam.springboottasks.service.TaskService;
import com.dteam.springboottasks.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.dteam.springboottasks.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final UserService userService;
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(value = "text", required = false) String text) {
        List<Task> tasks = (text == null) ? taskService.getTasks() : taskService.getTasksContainingText(text);
        return tasks.stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TaskDto createTask(@AuthenticationPrincipal CustomUserDetails currentUser,
                                @Valid @RequestBody CreateTaskRequest createTaskRequest) {
        User user = userService.validateAndGetUserByUsername(currentUser.getUsername());
        Task task = taskMapper.toTask(createTaskRequest);
        task.setId(UUID.randomUUID().toString());
        task.setUser(user);
        return taskMapper.toTaskDto(taskService.saveTask(task));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("/{id}")
    public TaskDto deleteTasks(@PathVariable UUID id) {
        Task task = taskService.validateAndGetTask(id.toString());
        taskService.deleteTask(task);
        return taskMapper.toTaskDto(task);
    }
}

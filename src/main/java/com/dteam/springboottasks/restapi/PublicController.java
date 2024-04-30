package com.dteam.springboottasks.restapi;

import com.dteam.springboottasks.service.TaskService;
import com.dteam.springboottasks.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class PublicController {

    private final UserService userService;
    private final TaskService taskService;
    @GetMapping("/numberOfUsers")
    public Integer getNumberOfUsers() {
        return userService.getUsers().size();
    }

    @GetMapping("/numberOfTasks")
    public Integer getNumberOfTasks() {
        return taskService.getTasks().size();
    }
}

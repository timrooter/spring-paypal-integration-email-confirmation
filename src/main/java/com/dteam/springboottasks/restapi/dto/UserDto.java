package com.dteam.springboottasks.restapi.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record UserDto(Long id, String username, String name, String email, String role, List<TaskDto> tasks) {

    public record TaskDto(String id, String title, String description, ZonedDateTime createdAt) {
    }
}
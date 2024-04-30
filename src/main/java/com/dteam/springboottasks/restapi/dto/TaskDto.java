package com.dteam.springboottasks.restapi.dto;

import java.time.ZonedDateTime;

public record TaskDto(String id, String title, String description, boolean isCompleted, TaskDto.UserDto user, ZonedDateTime createdAt) {

    public record UserDto(String username){
    }
}

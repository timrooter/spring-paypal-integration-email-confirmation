package com.dteam.springboottasks.mapper;

import com.dteam.springboottasks.model.User;
import com.dteam.springboottasks.restapi.dto.UserDto;

public interface UserMapper {

    UserDto toUserDto(User user);
}
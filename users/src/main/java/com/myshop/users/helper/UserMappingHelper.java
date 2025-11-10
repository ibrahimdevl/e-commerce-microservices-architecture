package com.myshop.users.helper;

import com.myshop.users.dtos.ResUserDto;
import com.myshop.users.dtos.UserDto;
import com.myshop.users.entities.User;

public interface UserMappingHelper {

     static ResUserDto mapToDto(final User user) {

        return ResUserDto.builder()
                .UserId(user.getUserId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

    }

     static User mapToUser(final UserDto userDto) {

        return User.builder()
                .username(userDto.username())
                .firstname(userDto.firstname())
                .lastname(userDto.lastname())
                .email(userDto.email())
                .password(userDto.password())
                .role(userDto.role())
                .build();

    }
}

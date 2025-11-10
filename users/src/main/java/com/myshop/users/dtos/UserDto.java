package com.myshop.users.dtos;

import com.myshop.users.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDto(
        @NotNull
        @NotEmpty
        String username,


        @NotNull(message = "firstname cannot be null")
        @NotEmpty
        String firstname,
        @NotNull
        @NotEmpty
        String lastname,
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotNull
        @NotEmpty
        String password,

        Role role
) {
}

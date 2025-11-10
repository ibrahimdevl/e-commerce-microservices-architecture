package com.myshop.order.dto;

import lombok.Builder;

@Builder
public record UserDto(

        Integer UserId,
        String username,

        String firstname,

        String lastname,

        String email

       ) {
}

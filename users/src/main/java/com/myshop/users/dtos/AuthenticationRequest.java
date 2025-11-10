package com.myshop.users.dtos;

public record AuthenticationRequest(String username,
                                    String password
) {
}

package com.myshop.users.services;

import com.myshop.users.dtos.AuthenticationRequest;
import com.myshop.users.dtos.AuthenticationResponse;
import com.myshop.users.dtos.ResUserDto;
import com.myshop.users.dtos.UserDto;
import com.myshop.users.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUsersService {

    ResUserDto addUser(UserDto userDto);

    Optional<User> loadUserByUsername(String username);

    List<ResUserDto> listUsers();

    ResUserDto findUserById(Integer id);

    ResUserDto update(final UserDto userDto);

    ResUserDto update(final Integer userID, final UserDto userDto);

    void deleteById(final Integer productId);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request,
                      HttpServletResponse response) throws IOException;


}

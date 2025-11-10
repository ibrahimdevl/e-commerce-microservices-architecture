package com.myshop.users.controllers;

import com.myshop.users.dtos.AuthenticationRequest;
import com.myshop.users.dtos.AuthenticationResponse;
import com.myshop.users.dtos.ResUserDto;
import com.myshop.users.dtos.UserDto;
import com.myshop.users.services.IUsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUsersService service;


    @PostMapping("/register")
    public ResUserDto addUser(
            @RequestBody UserDto request ) {
        return service.addUser(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    )  {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<ResUserDto> findById(
            @PathVariable("userId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String userId) {
        log.info("*** UserDto, controller; fetch user by id : "+ userId );
        return ResponseEntity.ok(this.service.findUserById(Integer.parseInt(userId)));
    }

    @PutMapping
    public ResponseEntity<ResUserDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final UserDto userDto) {
        log.info("*** UserDto, controller; update user *");
        return ResponseEntity.ok(this.service.update(userDto));
    }

    @DeleteMapping("user/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId") final String userId) {
        log.info("*** Boolean, resource; delete user by id *");
        this.service.deleteById(Integer.parseInt(userId));
        return ResponseEntity.ok(true);
    }

    @GetMapping("/users")
    public List<ResUserDto> getUsers( ) {
        log.info("*** UserDto, controller; fetch all users");
        return service.listUsers();
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response

    ) throws IOException {
        service.refreshToken(request,response);
    }





}

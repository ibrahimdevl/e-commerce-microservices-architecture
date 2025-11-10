package com.myshop.users.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myshop.users.dtos.ResUserDto;
import com.myshop.users.dtos.UserDto;
import com.myshop.users.entities.User;
import com.myshop.users.helper.UserMappingHelper;
import com.myshop.users.repositories.UserRepository;
import com.myshop.users.security.JwtService;
import com.myshop.users.services.servicesImpl.IUserServiceImpl;
import com.myshop.users.token.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Optional;

import static com.myshop.users.entities.Role.ADMIN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserServiceImpl userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenRepository tokenRepository;

    @InjectMocks
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;


    private UserDto userDto;
    private ResUserDto resUserDto;



    @BeforeEach
    void setUp() {
        userDto = UserDto.builder()
                .username("Admin")
                .firstname("Admin")
                .lastname("Admin")
                .email("admin@mail.com")
                .password("password")
                .role(ADMIN)
                .build();

        resUserDto = ResUserDto.builder()
                .username("Admin")
                .firstname("Admin")
                .lastname("Admin")
                .email("admin@mail.com")
                .role(ADMIN)
                .build();
        MockitoAnnotations.openMocks(this);
        userService = new IUserServiceImpl(userRepository, passwordEncoder, authenticationManager, jwtService, tokenRepository);
        userController = new UserController(userService);
    }


    @Test
    void addUser() throws Exception {
        given(userService.addUser(userDto)).willReturn(resUserDto); // Stubbing userService behavior

        ResultActions resultActions = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)));

        resultActions.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void authenticate() {
    }

    @Test
    @Disabled("À corriger : Content-Type manquant")
    void findById_whenUserExists_returnsUser() throws Exception {


        // Arrange
        int userId = 1;

        User existingUser = UserMappingHelper.mapToUser(userDto);

        //when(userService.findUserById(userId)).thenReturn(resUserDto);
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        // Act
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/auth/user/{userId}", userId));

        // Assert
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value(resUserDto.username())) // Adjust the JSON path based on your response structure
                // Add more assertions as needed
                .andReturn();


    }


    @Test
    void findById_whenUserDoesNotExist_throwsException() {


    }


    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getUsers() {

    }

    @Test
    void refreshToken() {
    }
}
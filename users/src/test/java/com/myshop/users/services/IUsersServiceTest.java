package com.myshop.users.services;

import com.myshop.users.dtos.AuthenticationRequest;
import com.myshop.users.dtos.AuthenticationResponse;
import com.myshop.users.dtos.ResUserDto;
import com.myshop.users.dtos.UserDto;
import com.myshop.users.entities.User;
import com.myshop.users.exceptions.wrapper.UserNotFoundException;
import com.myshop.users.helper.UserMappingHelper;
import com.myshop.users.repositories.UserRepository;
import com.myshop.users.security.JwtService;
import com.myshop.users.services.servicesImpl.IUserServiceImpl;
import com.myshop.users.token.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.myshop.users.entities.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IUsersServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private TokenRepository tokenRepository;
    @InjectMocks
    private IUserServiceImpl userService;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        userService = new IUserServiceImpl(userRepository, passwordEncoder, authenticationManager, jwtService, tokenRepository);
    }


    @Test
    void testAddUser_withValidInputs() {
        UserDto input = createValidUserDto();
        ResUserDto result = userService.addUser(input);

        assertNotNull(result);
        assertEquals(input.firstname(), result.firstname());
        assertEquals(input.lastname(), result.lastname());
        assertEquals(input.lastname(), result.lastname());
        assertEquals(input.email(), result.email());
        verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    private UserDto createValidUserDto() {
        return new UserDto(
                "John",
                "Doe",
                "zigeni",
                "<EMAIL>",
                "123456"
                , ADMIN);

    }

    @Test
    void loadUserByUsername() {
        User user = new User();
        user.setUsername("John");
        user.setPassword("<PASSWORD>");
        user.setRole(ADMIN);
        when(userRepository.findByUsername("John")).thenReturn(Optional.of(user));
        Optional<User> result = userService.loadUserByUsername("John");
        assertNotNull(result);
        result.ifPresent(u -> {
            assertEquals(user.getUsername(), u.getUsername());
            assertEquals(user.getPassword(), u.getPassword());
            assertEquals(user.getRole(), u.getRole());
        });

    }

    @Test
    void listUsers() {
        //when
        userService.listUsers();
        //then
        verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findUserByIdTest() {
        // Given
        Integer userId = 1;

        User existingUser = UserMappingHelper.mapToUser(createValidUserDto());

        // Mocking behavior for findById operation
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // When
        ResUserDto result = userService.findUserById(userId);

        // Then
        assertNotNull(result);
        assertEquals(existingUser.getUsername(), result.username());
        assertEquals(existingUser.getFirstname(), result.firstname());
        assertEquals(existingUser.getLastname(), result.lastname());
        assertEquals(existingUser.getEmail(), result.email());
        assertEquals(existingUser.getRole(), result.role());
        // Verify that findById method of userRepository is called with the correct userId
        verify(userRepository).findById(userId);

    }

    @Test
    void findUserByIdNotFoundTest() {
        // Given
        Integer userId = 1;

        // Mocking behavior for findById operation returning empty Optional
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(UserNotFoundException.class, () -> {
            userService.findUserById(userId);
        });

        // Verify that findById method of userRepository is called with the correct userId
        verify(userRepository).findById(userId);

        // You can add more verification as needed for other method calls.
    }

    @Test
    void update() {

        // Given
        UserDto userDto = createValidUserDto();
        User userToUpdate = UserMappingHelper.mapToUser(userDto);

        // Mocking behavior for the save operation
        when(userRepository.save(userToUpdate)).thenReturn(userToUpdate);

        // When
        ResUserDto result = userService.update(userDto);

        // Then
        assertNotNull(result);
        assertEquals(userDto.firstname(), result.firstname());
        assertEquals(userDto.lastname(), result.lastname());
        assertEquals(userDto.lastname(), result.lastname());
        assertEquals(userDto.email(), result.email());
        verify(userRepository, times(1)).save(any(User.class));
        // Verify that save method of userRepository is called with the correct user
        verify(userRepository).save(userToUpdate);

    }

    @Test
    void testUpdate() {
    }


    @Test
    void deleteByIdTest() {
        // Given
        Integer userId = 1;

        // Mocking behavior for user not found
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteById(userId);
        });

        // Verify that findById method of userRepository is called with the correct userId
        verify(userRepository).findById(userId);

        // Verify that delete method of userRepository is not called
        verify(userRepository, never()).delete(any());

        // You can add more verification as needed for other method calls.
    }

    @Test
    void deleteByIdSuccessTest() {
        // Given
        Integer userId = 1;
        User userToDelete = new User(); // initialize with required fields

        // Mocking behavior for user found
        when(userRepository.findById(userId)).thenReturn(Optional.of(userToDelete));

        // When
        userService.deleteById(userId);

        // Then
        // Verify that findById method of userRepository is called with the correct userId
        verify(userRepository).findById(userId);

        // Verify that delete method of userRepository is called with the correct user
        verify(userRepository).delete(userToDelete);

    }

    @Test
    void authenticate() {

        // Given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");
        User user = new User();
        // Initialize with required fields for testing

        when(userRepository.findByUsername(authenticationRequest.username())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("fakeAccessToken");
        when(jwtService.generateRefreshToken(user)).thenReturn("fakeRefreshToken");

        // When
        AuthenticationResponse result = userService.authenticate(authenticationRequest);

        // Then
        assertNotNull(result);
        assertEquals("fakeAccessToken", result.getAccessToken());
        assertEquals("fakeRefreshToken", result.getRefreshToken());

        // Verify that authenticate method of authenticationManager is called
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Verify that findByUsername method of userRepository is called with the correct username
        verify(userRepository).findByUsername("username");

        // Verify that generateToken and generateRefreshToken methods of jwtService are called with the correct user
        verify(jwtService).generateToken(user);
        verify(jwtService).generateRefreshToken(user);

    }

    @Test
    void Authenticate_withInvalidUser_shouldThrowUserNotFoundException() {
        // Given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");

        // Mocking behavior for user not found
        when(userRepository.findByUsername(authenticationRequest.username())).thenReturn(Optional.empty());

        // When and Then
        assertThrows(UserNotFoundException.class, () -> {
            userService.authenticate(authenticationRequest);
        });

        // Verify that authenticate method of authenticationManager is called
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Verify that findByUsername method of userRepository is called with the correct username
        verify(userRepository).findByUsername("username");

        // Verify that generateToken and generateRefreshToken methods of jwtService are not called
        verify(jwtService, never()).generateToken(any());
        verify(jwtService, never()).generateRefreshToken(any());


    }

    @Test
    @Disabled
    void refreshToken() {
    }
}
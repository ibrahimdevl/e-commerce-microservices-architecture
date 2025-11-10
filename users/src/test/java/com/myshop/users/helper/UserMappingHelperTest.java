package com.myshop.users.helper;

import com.myshop.users.dtos.ResUserDto;
import com.myshop.users.dtos.UserDto;
import com.myshop.users.entities.User;
import org.junit.jupiter.api.Test;
import static com.myshop.users.entities.Role.*;

import static org.junit.jupiter.api.Assertions.*;

class UserMappingHelperTest {


    @Test
    void mapToDto() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUsername("john123");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john@email.com");
        user.setRole(ADMIN);

        // Act
        ResUserDto userDto = UserMappingHelper.mapToDto(user);

        // Assert
        assertNotNull(userDto);
        assertEquals(1, userDto.UserId());
        assertEquals("john123", userDto.username());
        assertEquals("John", userDto.firstname());
        assertEquals("Doe", userDto.lastname());
        assertEquals("john@email.com", userDto.email());
        assertEquals(ADMIN, userDto.role());

    }

    @Test
    void mapToUser() {

        // Arrange
        UserDto userDto = new UserDto("john123", "John", "Doe", "john@email.com", "123456",   ADMIN);

        // Act
        User user = UserMappingHelper.mapToUser(userDto);

        // Assert
        assertNotNull(user);
        assertEquals("john123", user.getUsername());
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("john@email.com", user.getEmail());
        assertEquals("123456", user.getPassword());
        assertEquals(ADMIN, userDto.role());
    }
}
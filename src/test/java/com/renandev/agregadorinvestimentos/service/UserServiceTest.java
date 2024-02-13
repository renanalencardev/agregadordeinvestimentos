package com.renandev.agregadorinvestimentos.service;

import com.renandev.agregadorinvestimentos.controller.CreateUserDto;
import com.renandev.agregadorinvestimentos.entity.User;
import com.renandev.agregadorinvestimentos.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;
    
    @Nested
    class createUser{

        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSucess() {
            //Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var input = new CreateUserDto(
                    "username",
                    "email@email.com",
                    "1234");
            //Act
            var output = userService.createUser(input);

            //Assert
            assertNotNull(output);

            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {
            //Arrange
            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDto(
                    "username",
                    "email@email.com",
                    "1234");
            //Act & Assert
            assertThrows(RuntimeException.class,() -> userService.createUser(input));

        }
    }
    
    @Nested
    class getUserById{

        @Test
        @DisplayName("Should get user by id whith Sucess when optional is present")
        void shouldGetUserByIdWithSucessWhenOptionalIsPresent() {
            //Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());
            //Act

            var output = userService.getUserById(user.getUserId().toString());
            //Assert

            assertTrue(output.isPresent());
            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());
        }
        @Test
        @DisplayName("Should get user by id whith Sucess when optional is empty")
        void shouldGetUserByIdWithSucessWhenOptionalIsEmpty() {
            //Arrange
            var userId = UUID.randomUUID();
            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());
            //Act

            var output = userService.getUserById(userId.toString());
            //Assert

            assertTrue(output.isEmpty());
            assertEquals(userId, uuidArgumentCaptor.getValue());
        }
    }

    @Nested
    class listUsers{
        @Test
        @DisplayName("Should return all users with sucess")
        void shouldReturnAllUsersWithSucess() {
            //Arrange
            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            var listUsers = List.of(user);
            doReturn(List.of(user)).when(userRepository).findAll();
            //Act
            var output = userService.listUsers();

            //Assert
            assertNotNull(output);
            assertEquals(listUsers.size(), output.size());

        }
    }
}
package com.renandev.agregadorinvestimentos.service;

import com.renandev.agregadorinvestimentos.controller.CreateUserDto;
import com.renandev.agregadorinvestimentos.controller.UpdateUserDto;
import com.renandev.agregadorinvestimentos.entity.User;
import com.renandev.agregadorinvestimentos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);
        var userSaved  = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
       return userRepository.findAll();
    }
    public void updateUserById(String userId, UpdateUserDto updateUserDto){
        var userEntity = userRepository.findById(UUID.fromString(userId));
        if(userEntity.isPresent()){
            var user = userEntity.get();
            if(updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }
            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);
        }
    }

    public void deleteById(String userId){
        var userExists = userRepository.existsById(UUID.fromString(userId));
        if (userExists){
            userRepository.deleteById(UUID.fromString(userId));
        }
    }

}

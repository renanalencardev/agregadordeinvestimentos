package com.renandev.agregadorinvestimentos.controller;

import com.renandev.agregadorinvestimentos.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto body){
        return null;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("UserId") String userId){
        return null;
    }

}

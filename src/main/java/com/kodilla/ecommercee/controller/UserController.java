package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.KeyDto;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable int userId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}")
    public KeyDto generateKey(@PathVariable int userId){
        return new KeyDto();
    }
}

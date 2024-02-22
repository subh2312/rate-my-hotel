package com.subhankar.userservice.controller;

import com.subhankar.userservice.model.DO.UserDO;
import com.subhankar.userservice.model.DTO.ResponseDTO;
import com.subhankar.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> saveUser(@RequestBody UserDO userDO) {
        return userService.saveUser(userDO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable String id, @RequestBody UserDO userDO) {
        return userService.updateUser(id, userDO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}

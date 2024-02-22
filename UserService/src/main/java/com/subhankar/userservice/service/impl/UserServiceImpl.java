package com.subhankar.userservice.service.impl;

import com.subhankar.userservice.model.DO.UserDO;
import com.subhankar.userservice.model.DTO.ResponseDTO;
import com.subhankar.userservice.repository.UserRepository;
import com.subhankar.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public ResponseEntity<ResponseDTO> saveUser(UserDO userDO) {
        return new ResponseEntity<>(new ResponseDTO("User saved successfully","201",userRepository.save(userDO)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getUser(String id) {
        return userRepository.findById(id).map(userDO -> new ResponseEntity<>(new ResponseDTO("User saved successfully","200",userDO), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ResponseDTO("User not found","404",null), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUser(String id, UserDO userDO) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteUser(String id) {

        return userRepository.findById(id).map(userDO -> {
            userRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseDTO("User deleted successfully","200",null), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(new ResponseDTO("User not found","404",null), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllUsers() {
        return new ResponseEntity<>(new ResponseDTO("User saved successfully","200",userRepository.findAll()), HttpStatus.OK);
    }
}

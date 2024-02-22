package com.subhankar.userservice.service;

import com.subhankar.userservice.model.DO.UserDO;
import com.subhankar.userservice.model.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<ResponseDTO> saveUser(UserDO userDO);
    ResponseEntity<ResponseDTO> getUser(String id);
    ResponseEntity<ResponseDTO> updateUser(String id, UserDO userDO);
    ResponseEntity<ResponseDTO> deleteUser(String id);
    ResponseEntity<ResponseDTO> getAllUsers();
}

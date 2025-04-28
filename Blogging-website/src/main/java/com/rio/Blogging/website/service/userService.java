package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface userService {
    public ResponseEntity<?> createNewUser(UserDto user);
    public ResponseEntity<?> getUser(Long id);
    public ResponseEntity<?> updateUser(Long id, UserDto userdto);
    public ResponseEntity<?> deleteUser(Long id);
    public ResponseEntity<?> getAllUsers();

}

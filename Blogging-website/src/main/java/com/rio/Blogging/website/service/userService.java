package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface userService {
    public ResponseEntity<UserDto> createUser(UserDto user);
    public ResponseEntity<UserDto> getUser(Long id);
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userdto);
    public void deleteUser(Long id);
    public ResponseEntity<List<UserDto>> getAllUsers();

}

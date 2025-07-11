package com.rio.Blogging.website.service;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.ReqObj.validOTPObj;
import com.rio.Blogging.website.ReqObj.veriAcc;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface userService {
    public ResponseEntity<?> createNewUser(UserDto user);

    public ResponseEntity<?> getUser(Long id);

    public ResponseEntity<?> updateUser(Long id, UserDto userdto);

    public ResponseEntity<?> deleteUser(Long id);

    public ResponseEntity<?> getAllUsers();

    public ResponseEntity<?> cheackUser(veriAcc userDto);

    public ResponseEntity<?> validateOTP(validOTPObj in_otp);

    public ResponseEntity<?> sentOTP(String username);
}

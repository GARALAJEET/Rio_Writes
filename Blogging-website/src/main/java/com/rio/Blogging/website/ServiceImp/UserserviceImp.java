package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.service.userService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class UserserviceImp implements userService {


    @Override
    public ResponseEntity<UserDto> createUser(UserDto user) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, UserDto userdto) {
        return null;
    }



    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {


        return null;
    }
    public User dtoToUser(UserDto userDto){
        User user=new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }
    public UserDto userToUserDto(User user){
        UserDto userDto=new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}

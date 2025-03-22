package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.repo.useRepo;
import com.rio.Blogging.website.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserserviceImp implements userService {
    @Autowired
    private useRepo userRepo;

    @Override
    public ResponseEntity<String> createUser(UserDto user) {
        User cur_user=dtoToUser(user);
        User us=userRepo.save(cur_user);
        if(us!=null){
            return new ResponseEntity<>("User Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User not Created",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getUser(Long id) {
        Optional<User> user=userRepo.findById(Math.toIntExact(id));
        if(user.isPresent()){
            UserDto cur_userDto = userToUserDto(user.get());
            return new ResponseEntity<>(cur_userDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
        }
//
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, UserDto userdto) {
        Optional<User> user=userRepo.findById(Math.toIntExact(id));
        if(user.isPresent()){
            User cur_user=dtoToUser(userdto);
           User up_user=new User();
                up_user.setName(cur_user.getName());
                up_user.setEmail(cur_user.getEmail());
                up_user.setAbout(cur_user.getAbout());
                up_user.setPassword(cur_user.getPassword());
                User us=userRepo.save(up_user);
                if(us!=null){
                    return new ResponseEntity<>("User Updated", HttpStatus.OK);
                }

        }
        return new ResponseEntity<>("User not Updated",HttpStatus.BAD_REQUEST);
    }



    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<User> user = userRepo.findById(Math.toIntExact(id));
        if (user.isPresent()) {
            userRepo.delete(user.get());
            return new ResponseEntity<>("User Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not Deleted", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepo.findAll();
        if(!users.isEmpty()){
            List<UserDto> userDtoList=new ArrayList<>();
            for(User user:users){
                UserDto userDto=userToUserDto(user);
                userDtoList.add(userDto);
            }
            return new ResponseEntity<>(userDtoList,HttpStatus.OK);
        }
        return new ResponseEntity<>("No Users Found",HttpStatus.NOT_FOUND);
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

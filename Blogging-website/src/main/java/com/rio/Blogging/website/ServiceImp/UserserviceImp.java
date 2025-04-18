package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.repo.userRepo;
import com.rio.Blogging.website.service.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserserviceImp implements userService {
    @Autowired
    private userRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ResponseEntity<String> createUser(UserDto user) {
        User cur_user=dtoToUser(user);
        Optional<User> s1=userRepo.findByEmail(user.getEmail());
        if(s1.isPresent()){
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        Optional<User>s2=userRepo.findByUsername(user.getusername());
        if(s2.isPresent()){
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        User us=userRepo.save(cur_user);
        if(us!=null){

            try {
                 SimpleMailMessage msg = new SimpleMailMessage();
                 msg.setTo(cur_user.getEmail());
                 msg.setSubject("Registration Successful");
                 msg.setText("Hello "+cur_user.getusername()+"\n\nYou have successfully registered with us.\n\nThank you for registering with us.\n\nRegards,\nRio");
                 javaMailSender.send(msg);
            } catch (Exception e) {
                return new ResponseEntity<>("Registration Failed", HttpStatus.BAD_REQUEST);
            }
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
        Optional<User> userOpt = userRepo.findById(Math.toIntExact(id));

        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();


            existingUser.setUsername(userdto.getusername());
            existingUser.setEmail(userdto.getEmail());
            existingUser.setAbout(userdto.getAbout());
            existingUser.setPassword(userdto.getPassword());

            User updatedUser = userRepo.save(existingUser);

            return new ResponseEntity<>("User Updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }




    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        Optional<User> user = userRepo.findById(Math.toIntExact(id));
        if (user.isPresent()) {
            userRepo.delete(user.get());
            return new ResponseEntity<>("User Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not Found", HttpStatus.BAD_REQUEST);
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
        User user=modelMapper.map(userDto,User.class);
        return user;
    }
    public UserDto userToUserDto(User user){

        UserDto userDto=modelMapper.map(user,UserDto.class);
        return userDto;
    }
}

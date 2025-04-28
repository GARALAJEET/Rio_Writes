package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.Modal.otp_verification;
//import com.rio.Blogging.website.ReqObj.validOTP;
import com.rio.Blogging.website.ReqObj.validOTPObj;
import com.rio.Blogging.website.feature.emailSender;
import com.rio.Blogging.website.feature.otpGenerator;
import com.rio.Blogging.website.repo.otpRepo;
import com.rio.Blogging.website.repo.userRepo;
import com.rio.Blogging.website.resMsg.validOtp;
import com.rio.Blogging.website.service.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserserviceImp implements userService {
    @Autowired
    private userRepo userRepo;
    @Autowired
    private otpRepo otpRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private emailSender mailsender;
    @Autowired
    private otpGenerator optgen;
    @Override
    public ResponseEntity<?> createNewUser(UserDto user) {
        User cur_user = dtoToUser(user);
        Optional<User> s1 = userRepo.findByEmail(user.getEmail());
        if (s1.isPresent()) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }
        Optional<User> s2 = userRepo.findByUsername(user.getusername());
        if (s2.isPresent()) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }

        userRepo.save(cur_user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
   public ResponseEntity<?>sentOTP(UserDto userDto){
       String opt=optgen.generateOPT(userDto);
       User u=dtoToUser(userDto);
       boolean ansMail=mailsender.mailsendforOTP(u,opt);
         if(ansMail){
              return new ResponseEntity<>("OTP sent to your email",HttpStatus.OK);
         }
         else {
                userRepo.delete(u);
              return new ResponseEntity<>("Error in sending OTP",HttpStatus.BAD_REQUEST);
         }

   }
    public ResponseEntity<?> validateOTP(validOTPObj in_otp) {
        Optional<User> user=userRepo.findByEmail(in_otp.getEmail());
        if(user.isEmpty()){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        UserDto userDto=userToUserDto(user.get());
        Optional<otp_verification> otpVerification=otpRepo.findByUsernameAndOtp(in_otp.getEmail(),in_otp.getOtp());
        boolean ans=optgen.validateOTP(userDto.getusername(),in_otp.getOtp());
        User cur_user1=dtoToUser(userDto);
        if(ans){
            cur_user1.setIsvarified(true);
            userRepo.save(cur_user1);
            UserDto ud=userToUserDto(cur_user1);
            validOtp msg=new validOtp();
            msg.setMsg("User Created");
            msg.setUser(ud);
            CompletableFuture<?> ans1=mailsender.wellcomeEmail(cur_user1);
            if(ans1.isCompletedExceptionally()){
                return new ResponseEntity<>("Error in sending email",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }
        else {
            otpRepo.delete(otpVerification.get());
            return new ResponseEntity<>("Invalid OTP ",HttpStatus.BAD_REQUEST);
        }
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

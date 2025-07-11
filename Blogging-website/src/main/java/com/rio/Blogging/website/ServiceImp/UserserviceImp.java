package com.rio.Blogging.website.ServiceImp;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.Modal.otp_verification;
//import com.rio.Blogging.website.ReqObj.validOTP;
import com.rio.Blogging.website.ReqObj.validOTPObj;
import com.rio.Blogging.website.ReqObj.veriAcc;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private otpRepo otprepo;
    @Autowired
    private otpGenerator optgen;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
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
        cur_user.setPassword(encoder.encode(cur_user.getPassword()));
        userRepo.save(cur_user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @Override
   public ResponseEntity<?>sentOTP(String username){
         Optional<otp_verification>otp=otprepo.findByUsername(username);
         if(otp.isPresent()){
             String cu=otp.get().getUsername();
             if(cu.equals(username)){
                 otpRepo.delete(otp.get());
             }

         }

       String opt=optgen.generateOPT(username);
       Optional<User>user=userRepo.findByUsername(username);
       User u=user.get();
       boolean ansMail=mailsender.mailsendforOTP(u,opt);
         if(ansMail){
              return new ResponseEntity<>("OTP sent to your email",HttpStatus.OK);
         }
         else {
                userRepo.delete(u);
              return new ResponseEntity<>("Error in sending OTP",HttpStatus.BAD_REQUEST);
         }

   }
   @Override
    public ResponseEntity<?> validateOTP(validOTPObj in_otp) {
        Optional<User> user=userRepo.findByEmailAndIsVerifiedFalse(in_otp.getEmail());
        if(user.isEmpty()){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }

        UserDto userDto=userToUserDto(user.get());
        Optional<otp_verification> otpVerification=otpRepo.findByUsernameAndOtp(user.get().getusername(),in_otp.getOtp());
        System.out.printf("otpVerification: "+otpVerification);
        if(otpVerification.isEmpty()){
            return new ResponseEntity<>("Invalid OTP",HttpStatus.BAD_REQUEST);
        }
        boolean ans=optgen.validateOTP(userDto.getusername(),in_otp.getOtp());
        User cur_user1=dtoToUser(userDto);
        if(ans){
            cur_user1.setIsvarified(true);
            userRepo.save(cur_user1);
            otpRepo.delete(otpVerification.get());
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

            if (userdto.getusername() != null) {
                existingUser.setUsername(userdto.getusername());
            }

            if (userdto.getEmail() != null) {
                existingUser.setEmail(userdto.getEmail());
                existingUser.setIsvarified(false);
            }

            if (userdto.getAbout() != null) {
                existingUser.setAbout(userdto.getAbout());
            }

            if (userdto.getPassword() != null) {
                String hashedPassword = encoder.encode(userdto.getPassword());
                existingUser.setPassword(hashedPassword);
            }
            userRepo.save(existingUser);

            return ResponseEntity.ok("User updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
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
    @Override
    public ResponseEntity<?> cheackUser(veriAcc userDto) {
        Optional<User> us = userRepo.findByUsername(userDto.getUsername());
        if (us.isEmpty()) {
            return new ResponseEntity<>("user not found", HttpStatus.BAD_REQUEST);
        }

        User user = us.get();

        if (!user.getEmail().equals(userDto.getEmail())) {
            return new ResponseEntity<>("email does not match", HttpStatus.BAD_REQUEST);
        }

        if (!encoder.matches(userDto.getPassword(), user.getPassword())) {
            return new ResponseEntity<>("password does not match", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("user is found", HttpStatus.OK);
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

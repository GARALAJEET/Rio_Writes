package com.rio.Blogging.website.feature;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.otp_verification;
import com.rio.Blogging.website.repo.otpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class otpGenerator {
   @Autowired
    private otpRepo otpRepo;

   private SecureRandom secureRandom = new SecureRandom();
   public String generateOPT(String username){
        Long otpnum=secureRandom.nextLong(1000000);
        String otp=String.format("%06d",otpnum);
        otp_verification otpVerification=new otp_verification();
        otpVerification.setUsername(username);
        otpVerification.setOtp(otp);
        otpVerification.setCreatedAt(LocalDateTime.now());
        otpVerification.setExpireAt(LocalDateTime.now().plusMinutes(5));
       otpRepo.save(otpVerification);
        return otp;
   }
   public boolean validateOTP(String username,String in_otp){
       Optional<otp_verification>otpVerification=otpRepo.findByUsername(username);
       if(otpVerification.isPresent()){
           otp_verification cur=otpVerification.get();
           return cur.getOtp().equals(in_otp) &&  cur.getExpireAt().isAfter(LocalDateTime.now());
       }
       return false;
   }
}

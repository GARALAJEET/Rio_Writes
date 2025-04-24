package com.rio.Blogging.website.feature;

import com.rio.Blogging.website.repo.otpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class otpGenerator {
   @Autowired
    private otpRepo otpRepo;
   public String generateOPT(){
         StringBuilder otp = new StringBuilder();
//         for (int i = 0; i < 6; i++) {
//              int digit = (int) (Math.random() * 10);
//              otp.append(digit);
//         }

       int otpNum= (int) (Math.random() * 1000000);
         String otpString = String.valueOf(otpNum);
         return otp.toString();
   }
}

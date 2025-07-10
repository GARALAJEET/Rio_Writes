package com.rio.Blogging.website.backgroudScheduled;

import com.rio.Blogging.website.repo.otpRepo;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class optCleanup {
    private otpRepo otpRepo;
    public optCleanup(otpRepo otpRepo){
        this.otpRepo=otpRepo;
    }
    @Scheduled(fixedRate = 600*1000)
    @Transactional
    public void clearOtp(){
        Optional<Integer>otp=otpRepo.deleteAllByExpireAtBefore(LocalDateTime.now());
        System.out.println("otp number of delete"+otp.get()+LocalDateTime.now());
    }
}

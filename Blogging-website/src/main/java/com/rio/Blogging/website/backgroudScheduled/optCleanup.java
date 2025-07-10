package com.rio.Blogging.website.backgroudScheduled;

import com.rio.Blogging.website.Modal.otp_verification;
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
    @Scheduled(fixedRate = 60 * 60 * 1000)
    @Transactional
    public void clearOtp() {
       Optional<Long> count = otpRepo.deleteAllByExpireAtBefore(LocalDateTime.now());
        System.out.println("Deleted OTPs: " + count + " at " + LocalDateTime.now());
    }

}

package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.otp_verification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface otpRepo extends JpaRepository<otp_verification,Long> {

   Optional<otp_verification> findByUsername(String username);
   Optional<otp_verification>findByUsernameAndOtp(String username,String otp);
//   @Transactional
//   Optional<Integer> deleteAllByExpireAtBefore(LocalDateTime localDateTime);
   @Transactional
   Optional<Long>deleteAllByExpireAtBefore(LocalDateTime localDateTime);
}

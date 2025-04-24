package com.rio.Blogging.website.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class otp_verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long otp_id;
    String username;
    String otp;
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime expireAt;

    public Long getOtp_id() {
        return otp_id;
    }

    public void setOtp_id(Long otp_id) {
        this.otp_id = otp_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}

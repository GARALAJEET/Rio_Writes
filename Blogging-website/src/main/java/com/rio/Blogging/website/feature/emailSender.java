package com.rio.Blogging.website.feature;

import com.rio.Blogging.website.Modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@Service

public class emailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean mailsendforOTP(User cur_user,String otp) {


            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(cur_user.getEmail());
                msg.setSubject(" OTP for Registration");
            msg.setText("Your OTP is: " + otp+"\n\n OTP is valid for 5 minutes " +" \n\nBest regards,\nThe Rio writes Team");
                javaMailSender.send(msg);
                return true;
            } catch (Exception e) {
                System.out.println("Error sending email: " + e.getMessage());
               return  false;
            }

    }
    @Async
    public CompletableFuture<?> wellcomeEmail(User user) {
        Thread t1 = new Thread(() -> {
            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(user.getEmail());
                msg.setSubject("Welcome to Rio  writes");
                msg.setText("Hello " + user.getusername() + ",\n\nWelcome to our Blogging website! We are thrilled to have you on board.\n\nBest regards,\nThe Blogging Team");
                javaMailSender.send(msg);
                return;
            } catch (Exception e) {
                System.out.println("Error sending email: " + e.getMessage());
            }
        });
        t1.start();
        return CompletableFuture.completedFuture(false);
    }
}

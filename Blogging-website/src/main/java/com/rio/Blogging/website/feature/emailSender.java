package com.rio.Blogging.website.feature;

import com.rio.Blogging.website.Modal.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Async
    public CompletableFuture<Boolean> mailsend(User cur_user){
        AtomicBoolean ans= new AtomicBoolean(true);
        Thread thread =new Thread(()->{

            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(cur_user.getEmail());
                msg.setSubject("Registration Successful");
                msg.setText("Hello "+cur_user.getusername()+"\n\nYou have successfully registered with us.\n\nThank you for registering with us.\n\nRegards,\nRio");
                javaMailSender.send(msg);
            } catch (Exception e) {
                System.out.println("Error sending email: " + e.getMessage());
                ans.set(false);
            }
            System.out.println("Email sent to " + cur_user.getEmail());
        });
       if(ans.get()){
           return CompletableFuture.completedFuture(true);
       }
       return CompletableFuture.completedFuture(false);

    }
}

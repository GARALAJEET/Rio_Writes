package com.rio.Blogging.website.feature;

import com.rio.Blogging.website.Modal.User;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class emailSender {

private final JavaMailSender javaMailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(emailSender.class);

//    public emailSender(JavaMailSender javaMailSender){
//        this.javaMailSender=javaMailSender;
//    }


    public boolean mailSendForOTP(User cur_user, String otp) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String htmlContent = loadEmailTemplate("otp-email-template.html");
            htmlContent = htmlContent.replace("[USER_NAME]", cur_user.getusername());
            htmlContent = htmlContent.replace("[OTP_CODE]", otp);

            helper.setText(htmlContent, true); // true indicates the content is HTML
            helper.setTo(cur_user.getEmail());
            helper.setSubject("Your OTP for Rio writes");
            // Consider setting a 'from' address if it's not configured globally
            // helper.setFrom("no-reply@riowrites.com");

            javaMailSender.send(mimeMessage);
            return true;

        } catch (Exception e) {

            System.out.println("Error sending email: " + e.getMessage());
            return false;
        }
    }

@Async
public CompletableFuture<Boolean> sendWelcomeEmail(User user) {
    try {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlContent = loadEmailTemplate("welcome-email.html");

        htmlContent = htmlContent.replace("[USER_NAME]", user.getusername());


        helper.setText(htmlContent, true);
        helper.setTo(user.getEmail());
        helper.setSubject("🎉 Welcome to Rio writes!");

        javaMailSender.send(mimeMessage);
        LOGGER.info("Welcome email sent successfully to {}", user.getEmail());

    } catch (Exception e) {
        LOGGER.error("Error sending welcome email to {}: {}", user.getEmail(), e.getMessage());
    }
    return CompletableFuture.completedFuture(false);
}


    private String loadEmailTemplate(String templatePath) {
        try {
            ClassPathResource resource = new ClassPathResource(templatePath);

            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("Error loading email template: {}", templatePath, e);

            return "Welcome! We are happy to have you.";
        }
    }
}

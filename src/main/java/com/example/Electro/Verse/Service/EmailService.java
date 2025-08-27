package com.example.Electro.Verse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String toEmail, String userName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-email@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Registration Confirmation - ElectroVerse 2k25");
        message.setText(
                "Hi " + userName + ",\n\n" +
                        "Thank you for registering for ElectroVerse 2k25!\n" +
                        "You will receive further updates about schedules and instructions soon.\n\n" +
                        "Regards,\nElectroVerse Team"
        );

        mailSender.send(message);
    }
}


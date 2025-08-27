package com.example.Electro.Verse.Service;

import com.example.Electro.Verse.Dto.RegistrationRequest;
import com.example.Electro.Verse.Entity.Registration;
import com.example.Electro.Verse.Repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final JavaMailSender mailSender;

    // Map of event IDs to human-readable names
    private final Map<String, String> eventMap = Map.of(
            "paper-presentation", "Paper Presentation",
            "technical-quiz", "Technical Quiz",
            "circuit-debugging", "Circuit Debugging",
            "project-expo", "Project Expo",
            "start-music", "Start Music",
            "squid-game", "Squid Game",
            "minute-to-win", "Minute to Win",
            "poster-creation", "Poster Creation"
    );

    public Registration saveRegistration(RegistrationRequest request) {
        // 1️⃣ Save registration to DB
        Registration registration = Registration.builder()
                .leaderName(request.getLeaderName())
                .leaderCollege(request.getLeaderCollege())
                .leaderEmail(request.getLeaderEmail())
                .leaderDepartment(request.getLeaderDepartment())
                .leaderYear(request.getLeaderYear())
                .memberName(request.getMemberName())
                .memberCollege(request.getMemberCollege())
                .memberEmail(request.getMemberEmail())
                .memberDepartment(request.getMemberDepartment())
                .memberYear(request.getMemberYear())
                .events(String.join(",", request.getEvents()))
                .transactionId(request.getTransactionId())
                .createdAt(LocalDateTime.now())
                .build();

        registrationRepository.save(registration);

        // 2️⃣ Prepare event names list
        String eventsList = request.getEvents().stream()
                .map(eventMap::get)
                .collect(Collectors.joining(", "));

        // 3️⃣ Send email to leader
        sendHtmlEmail(registration.getLeaderEmail(), registration.getLeaderName(), "Team Leader", eventsList);

        // 4️⃣ Send email to member if email exists
        if (registration.getMemberEmail() != null && !registration.getMemberEmail().trim().isEmpty()) {
            sendHtmlEmail(registration.getMemberEmail(), registration.getMemberName(), "Team Member", eventsList);
        }

        return registration;
    }

    private void sendHtmlEmail(String toEmail, String userName, String role, String eventsList) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("your-email@gmail.com"); // replace with your email
            helper.setTo(toEmail);
            helper.setSubject("Welcome to ElectroVerse 2k25! Registration Confirmed");

            String htmlContent = """
                    <html>
                    <body style="font-family: Arial, sans-serif; background-color:#0f0f0f; color:#f5f5f5; padding:20px;">
                        <div style="max-width:600px; margin:auto; background-color:#1a1a1a; padding:30px; border-radius:15px; border: 2px solid #ffcc00;">
                            <h2 style="color:#ffcc00;">Hello %s (%s)!</h2>
                            <p>Thank you for registering for <strong>ElectroVerse 2k25</strong>.</p>
                            <p style="margin-top:20px;"><strong>Events Registered:</strong></p>
                            <p style="background-color:#333; padding:10px; border-radius:8px;">%s</p>
                            <p style="margin-top:20px;">We will send you further updates about schedules and instructions soon.</p>
                            <p style="margin-top:30px;">Regards,<br/><strong>ElectroVerse Team</strong></p>
                        </div>
                    </body>
                    </html>
                    """.formatted(userName, role, eventsList);

            helper.setText(htmlContent, true); // true → HTML content
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

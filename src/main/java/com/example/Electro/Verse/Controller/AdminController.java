package com.example.Electro.Verse.Controller;

import com.example.Electro.Verse.Entity.Registration;
import com.example.Electro.Verse.Repository.RegistrationRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final RegistrationRepository registrationRepository;

    // 🔹 New endpoint: fetch all registrations as JSON
    @GetMapping("/registrations")
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // 🔹 CSV Export
    @GetMapping("/registrations/export/csv")
    public void exportRegistrations(HttpServletResponse response) throws Exception {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=registrations.csv");

        PrintWriter writer = response.getWriter();
        writer.println("ID,Leader Name,Leader College,Leader Email,Leader Dept,Leader Year,Member Name,Member College,Member Email,Member Dept,Member Year,Events,Transaction ID,Created At");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for (Registration reg : registrationRepository.findAll()) {
            String events = String.join(";", reg.getEvents());
            String createdAt = reg.getCreatedAt().format(formatter);

            writer.printf("%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,\"%s\",%s,%s%n",
                    reg.getId(),
                    reg.getLeaderName(),
                    reg.getLeaderCollege(),
                    reg.getLeaderEmail(),
                    reg.getLeaderDepartment(),
                    reg.getLeaderYear(),
                    reg.getMemberName() != null ? reg.getMemberName() : "",
                    reg.getMemberCollege() != null ? reg.getMemberCollege() : "",
                    reg.getMemberEmail() != null ? reg.getMemberEmail() : "",
                    reg.getMemberDepartment() != null ? reg.getMemberDepartment() : "",
                    reg.getMemberYear() != null ? reg.getMemberYear() : "",
                    events,
                    "'" + reg.getTransactionId() + "'",
                    createdAt
            );
        }

        writer.flush();
        writer.close();
    }
}

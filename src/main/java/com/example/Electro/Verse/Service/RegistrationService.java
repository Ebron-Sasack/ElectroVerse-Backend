package com.example.Electro.Verse.Service;


import com.example.Electro.Verse.Dto.RegistrationRequest;
import com.example.Electro.Verse.Entity.Registration;
import com.example.Electro.Verse.Repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public Registration saveRegistration(RegistrationRequest request) {
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
        return registrationRepository.save(registration);
    }
}

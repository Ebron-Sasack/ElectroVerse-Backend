package com.example.Electro.Verse.Controller;


import com.example.Electro.Verse.Dto.RegistrationRequest;
import com.example.Electro.Verse.Entity.Registration;
import com.example.Electro.Verse.Service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Registration> createRegistration(@Valid @RequestBody RegistrationRequest request) {
        Registration registration = registrationService.saveRegistration(request);
        return ResponseEntity.ok(registration); // 200 OK
    }

}
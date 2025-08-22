package com.example.Electro.Verse.Controller;


import com.example.Electro.Verse.Dto.RegistrationRequest;
import com.example.Electro.Verse.Entity.Registration;
import com.example.Electro.Verse.Service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public Registration createRegistration(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.saveRegistration(request);
    }
}


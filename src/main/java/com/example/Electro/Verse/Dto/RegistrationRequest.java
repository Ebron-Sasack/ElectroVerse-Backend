package com.example.Electro.Verse.Dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.List;

@Data
public class RegistrationRequest {
    @NotBlank
    private String leaderName;
    @NotBlank
    private String leaderCollege;
    @Email
    private String leaderEmail;
    @NotBlank
    private String leaderDepartment;
    @NotBlank
    private String leaderYear;

    private String memberName;
    private String memberCollege;
    @Email
    private String memberEmail;
    private String memberDepartment;
    private String memberYear;

    @NotEmpty
    private List<String> events;

    @NotBlank
    private String transactionId;
}


package com.example.Electro.Verse.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leaderName;
    private String leaderCollege;
    private String leaderEmail;
    private String leaderDepartment;
    private String leaderYear;

    private String memberName;
    private String memberCollege;
    private String memberEmail;
    private String memberDepartment;
    private String memberYear;

    @Column(length = 1000)
    private String events; // Stored as comma-separated string

    private String transactionId;

    private LocalDateTime createdAt;
}


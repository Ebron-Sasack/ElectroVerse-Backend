package com.example.Electro.Verse.Repository;

import com.example.Electro.Verse.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}

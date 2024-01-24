package com.rjpinks.MarkwitzAutomotive.repository;

import com.rjpinks.MarkwitzAutomotive.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByEmail(String email);
    Profile findByUsername(String username);

    Profile findFirstByUsername(String username);
}

package com.rjpinks.MarkwitzAutomotive.service;

import com.rjpinks.MarkwitzAutomotive.dto.RegistrationDto;
import com.rjpinks.MarkwitzAutomotive.models.Profile;

public interface ProfileService {
    void saveProfile(RegistrationDto registrationDto);

    Profile findByEmail(String email);

    Profile findByUsername(String username);
}

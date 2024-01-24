package com.rjpinks.MarkwitzAutomotive.service.impl;

import com.rjpinks.MarkwitzAutomotive.dto.RegistrationDto;
import com.rjpinks.MarkwitzAutomotive.models.Profile;
import com.rjpinks.MarkwitzAutomotive.models.Role;
import com.rjpinks.MarkwitzAutomotive.repository.ProfileRepository;
import com.rjpinks.MarkwitzAutomotive.repository.RoleRepository;
import com.rjpinks.MarkwitzAutomotive.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ProfileServiceImpl implements ProfileService {
    private ProfileRepository profileRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveProfile(RegistrationDto registrationDto) {
        Profile profile = new Profile();
        profile.setUsername(registrationDto.getUsername());
        profile.setEmail(registrationDto.getEmail());
        profile.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        profile.setRoles(Collections.singletonList(role));
        profileRepository.save(profile);
    }

    @Override
    public Profile findByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public Profile findByUsername(String username) {
        return profileRepository.findByUsername(username);
    }
}

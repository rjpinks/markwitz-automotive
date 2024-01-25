package com.rjpinks.MarkwitzAutomotive.controller;

import com.rjpinks.MarkwitzAutomotive.dto.RegistrationDto;
import com.rjpinks.MarkwitzAutomotive.models.Profile;
import com.rjpinks.MarkwitzAutomotive.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private ProfileService profileService;

    public AuthController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto profile = new RegistrationDto();
        model.addAttribute("profile", profile);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("profile") RegistrationDto profile,
                           BindingResult result, Model model) {
        Profile existingProfileEmail = profileService.findByEmail(profile.getEmail());
        if(existingProfileEmail != null && existingProfileEmail.getEmail() != null && !existingProfileEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        Profile existingProfileUsername = profileService.findByUsername(profile.getUsername());
        if (existingProfileUsername != null && existingProfileUsername.getUsername() != null && !existingProfileUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if (result.hasErrors()) {
            model.addAttribute("profile", profile);
            return "register";
        }
        profileService.saveProfile(profile);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        RegistrationDto profile = new RegistrationDto();
        model.addAttribute("user", profile);
        return "login";
    }
}

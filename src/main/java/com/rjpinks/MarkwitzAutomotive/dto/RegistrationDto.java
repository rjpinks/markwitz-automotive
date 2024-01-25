package com.rjpinks.MarkwitzAutomotive.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private long id;
    @NotEmpty(message = "Must have a username")
    private String username;
    @Email
    @NotEmpty(message = "Must have a valid email")
    private String email;
    @NotEmpty(message = "Must have a password")
    private String password;
}

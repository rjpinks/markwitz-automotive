package com.rjpinks.MarkwitzAutomotive.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    @Email
    @NotEmpty(message = "Must have an email")
    private String email;
    @Min(value = 6, message = "Must be longer than 6")
    @NotEmpty(message = "Must have a password")
    private String password;
    private boolean admin;
    private long[] favorites;
}

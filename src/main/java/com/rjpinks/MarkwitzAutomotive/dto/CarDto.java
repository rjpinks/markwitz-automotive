package com.rjpinks.MarkwitzAutomotive.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
    private long id;

    @NotEmpty(message = "Must have a make")
    private String make;

    @NotEmpty(message = "Must have a model")
    private String model;

    @Positive(message = "Must have a year greater than zero")
    private int modelYear;
    
    private String carUrl;

    @Positive(message = "Must have milage greater than zero")
    private long milage;
}

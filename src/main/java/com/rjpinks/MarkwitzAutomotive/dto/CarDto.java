package com.rjpinks.MarkwitzAutomotive.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {
    private long id;
    private String make;
    private String model;
    private int modelYear;
    private String carUrl;
    private long milage;
}

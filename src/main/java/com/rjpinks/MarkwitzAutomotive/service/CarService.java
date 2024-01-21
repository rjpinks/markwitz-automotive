package com.rjpinks.MarkwitzAutomotive.service;

import com.rjpinks.MarkwitzAutomotive.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> findAllCars();
    List<CarDto> findAllCarsByMake(String make);
}

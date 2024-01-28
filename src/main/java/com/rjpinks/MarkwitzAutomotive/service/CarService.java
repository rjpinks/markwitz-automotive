package com.rjpinks.MarkwitzAutomotive.service;

import com.rjpinks.MarkwitzAutomotive.dto.CarDto;
import com.rjpinks.MarkwitzAutomotive.models.Car;

import java.util.List;

public interface CarService {
    List<CarDto> findAllCars();
    List<CarDto> findAllCarsByMake(String make);
    CarDto findCarById(long carId);
    Car saveCar(Car car);
    void updateCar(CarDto car);

    void delete(long carId);

    List<CarDto> searchCars(String query);
}

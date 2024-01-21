package com.rjpinks.MarkwitzAutomotive.service.impl;

import com.rjpinks.MarkwitzAutomotive.dto.CarDto;
import com.rjpinks.MarkwitzAutomotive.repository.CarRepository;
import com.rjpinks.MarkwitzAutomotive.models.Car;
import com.rjpinks.MarkwitzAutomotive.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> findAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map((car) -> mapToCarDto(car)).collect(Collectors.toList());
    }

    @Override
    public List<CarDto> findAllCarsByMake(String make) {
        List<Car> cars = carRepository.findByMake(make);
        return cars.stream().map((car) -> mapToCarDto(car)).collect(Collectors.toList());
    }

    private CarDto mapToCarDto(Car car) {
        CarDto carDto = CarDto.builder()
            .id(car.getId())
            .make(car.getMake())
            .model(car.getModel())
            .modelYear(car.getModelYear())
            .carUrl(car.getCarUrl())
            .build();

        return carDto;
    }

}

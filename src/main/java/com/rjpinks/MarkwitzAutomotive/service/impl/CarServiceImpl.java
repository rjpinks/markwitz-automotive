package com.rjpinks.MarkwitzAutomotive.service.impl;

import com.rjpinks.MarkwitzAutomotive.dto.CarDto;
import com.rjpinks.MarkwitzAutomotive.models.Profile;
import com.rjpinks.MarkwitzAutomotive.repository.CarRepository;
import com.rjpinks.MarkwitzAutomotive.models.Car;
import com.rjpinks.MarkwitzAutomotive.repository.ProfileRepository;
import com.rjpinks.MarkwitzAutomotive.security.SecurityUtil;
import com.rjpinks.MarkwitzAutomotive.service.CarService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ProfileRepository profileRepository) {
        this.carRepository = carRepository;
        this.profileRepository = profileRepository;
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

    @Override
    public Car saveCar(Car car) {
        String username = SecurityUtil.getSessionUser();
        Profile profile = profileRepository.findByUsername(username);
        return carRepository.save(car);
    }

    @Override
    public CarDto findCarById(long carId) {
        Car car = carRepository.findById(carId).get();
        return mapToCarDto(car);
    }

    @Override
    public void updateCar(CarDto carDto) {
        // Car car = mapToCar(carDto);
        // carRepository.save(car);
        Optional<Car> optionalCar = carRepository.findById(carDto.getId());

        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();
            existingCar.setMake(carDto.getMake());
            existingCar.setModel(carDto.getModel());
            existingCar.setModelYear(carDto.getModelYear());
            existingCar.setCarUrl(carDto.getCarUrl());
            existingCar.setMilage(carDto.getMilage());
    
            carRepository.save(existingCar);
        }
    }

    @Override
    public void delete(long carId) {
        carRepository.deleteById(carId);
    }

    private CarDto mapToCarDto(Car car) {
        CarDto carDto = CarDto.builder()
            .id(car.getId())
            .make(car.getMake())
            .model(car.getModel())
            .modelYear(car.getModelYear())
            .carUrl(car.getCarUrl())
            .milage(car.getMilage())
            .build();

        return carDto;
    }

}

package com.rjpinks.MarkwitzAutomotive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rjpinks.MarkwitzAutomotive.service.CarService;
import com.rjpinks.MarkwitzAutomotive.dto.CarDto;

@Controller
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String listCars(Model model) {
        List<CarDto> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
        return "home-page";
    }
}

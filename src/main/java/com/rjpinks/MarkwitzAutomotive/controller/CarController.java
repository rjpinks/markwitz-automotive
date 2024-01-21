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

    @GetMapping("/cars")
    public String listCars(Model model) {
        List<CarDto> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
        return "cars-page";
    }

    @GetMapping("/cars/Ford")
    public String listFordCars(Model model) {
        List<CarDto> cars = carService.findAllCarsByMake("Ford");
        model.addAttribute("cars", cars);
        return "ford-page";
    }

    @GetMapping("/cars/Dodge")
    public String listDodgeCars(Model model) {
        List<CarDto> cars = carService.findAllCarsByMake("Dodge");
        model.addAttribute("cars", cars);
        return "dodge-page";
    }

    @GetMapping("/cars/Chevy")
    public String listChevyCars(Model model) {
        List<CarDto> cars = carService.findAllCarsByMake("Chevrolet");
        model.addAttribute("cars", cars);
        return "chevy-page";
    }

    @GetMapping("/cars/Nissan")
    public String listNissanCars(Model model) {
        List<CarDto> cars = carService.findAllCarsByMake("Nissan");
        model.addAttribute("cars", cars);
        return "nissan-page";
    }

    @GetMapping("/cars/Mitsubishi")
    public String listMitsubishiCars(Model model) {
        List<CarDto> cars = carService.findAllCarsByMake("Mitsubishi");
        model.addAttribute("cars", cars);
        return "mitsubishi-page";
    }

    @GetMapping("/cars/Mazda")
    public String listMazdaCars(Model model) {
        List<CarDto> cars = carService.findAllCarsByMake("Mazda");
        model.addAttribute("cars", cars);
        return "mazda-page";
    }
}

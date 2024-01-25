package com.rjpinks.MarkwitzAutomotive.controller;

import java.util.List;

import com.rjpinks.MarkwitzAutomotive.models.Profile;
import com.rjpinks.MarkwitzAutomotive.security.SecurityUtil;
import com.rjpinks.MarkwitzAutomotive.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rjpinks.MarkwitzAutomotive.service.CarService;

import jakarta.validation.Valid;

import com.rjpinks.MarkwitzAutomotive.dto.CarDto;
import com.rjpinks.MarkwitzAutomotive.models.Car;

@Controller
public class CarController {
    private CarService carService;
    private ProfileService profileService;

    @Autowired
    public CarController(CarService carService, ProfileService profileService) {
        this.carService = carService;
        this.profileService = profileService;
    }

    // Reading Car Mappings

    @GetMapping("/")
    public String listCars(Model model) {
        Profile profile = new Profile();
        List<CarDto> cars = carService.findAllCars();
        String username = SecurityUtil.getSessionUser();
        System.out.println("getSessionUser = " + username);
        if (username != null) {
            profile = profileService.findByEmail(username);
            System.out.println(profile);
            model.addAttribute("profile", profile);
        }
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

    @GetMapping("/contact")
    public String displayContactInfo() {
        return "contact";
    }

    // Create Car Mappings

    @GetMapping("/new")
    public String newCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "new-car-form";
    }

    @PostMapping("/new")
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.saveCar(car);
        return "redirect:/";
    }

    // Update Car Mappings

    // I am not adding validations to this because I do not want the vin to be a part of the Dto, but it would allow for database sniffing if I put the validation constraints on the actual model.
    @GetMapping("/cars/{carId}/update")
    public String updateCarForm(@PathVariable("carId") long carId, Model model) {
        CarDto car = carService.findCarById(carId);
        model.addAttribute("car", car);
        return "update-car-form";
    }

    @PostMapping("/cars/{carId}/update")
    public String updateCar(@PathVariable("carId") long carId, @Valid @ModelAttribute("car") CarDto car, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("org.springframework.validation.BindingResult.car", result);
            return "update-car-form";
        }

        car.setId(carId);
        carService.updateCar(car);
        return "redirect:/";
    }

    //Routes for Delete
    @GetMapping("/cars/{carId}/delete")
    public String deleteCarPage(@PathVariable("carId") long carId) {
        carService.delete(carId);
        return "redirect:/";
    }
}

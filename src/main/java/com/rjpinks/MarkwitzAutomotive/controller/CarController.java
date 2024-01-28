package com.rjpinks.MarkwitzAutomotive.controller;

import java.util.List;
import java.util.Locale;

import com.rjpinks.MarkwitzAutomotive.models.Profile;
import com.rjpinks.MarkwitzAutomotive.security.SecurityUtil;
import com.rjpinks.MarkwitzAutomotive.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        if (username != null) {
            profile = profileService.findByEmail(username);
            model.addAttribute("profile", profile);
        }
        model.addAttribute("cars", cars);
        return "cars-page";
    }

    @GetMapping("/cars/{carMake}")
    public String listCarMake(@PathVariable("carMake") String carMake, Model model) {
        Profile profile = new Profile();
        if (carMake.equals("Chevy")) {
            carMake = "Chevrolet";
        }
        List<CarDto> cars = carService.findAllCarsByMake(carMake);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            profile = profileService.findByEmail(username);
            model.addAttribute("profile", profile);
        }
        model.addAttribute("cars", cars);
        return "cars-page";
    }

    @GetMapping("/clubs/search")
    public String searchCars(@RequestParam(value = "query") String query, Model model) {
        String[] splQuery = query.split("");
        splQuery[0] = splQuery[0].toUpperCase();
        StringBuilder newQuery = new StringBuilder();
        for (int i = 0; i < splQuery.length; i++) {
            newQuery.append(splQuery[i]);
        }
        String finalQuery = newQuery.toString();

        List<CarDto> cars = carService.searchCars(finalQuery);
        model.addAttribute("cars", cars);
        return "cars-page";
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

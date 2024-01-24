package com.rjpinks.MarkwitzAutomotive.controller;

import com.rjpinks.MarkwitzAutomotive.dto.UserDto;
import com.rjpinks.MarkwitzAutomotive.models.User;
import com.rjpinks.MarkwitzAutomotive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/users/sign-up")
    public String saveUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign-up-form";
    }

    @PostMapping("/users/sign-up")
    public String saveUser(@ModelAttribute("user") UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/";
    }
}

package com.rjpinks.MarkwitzAutomotive.service.impl;

import com.rjpinks.MarkwitzAutomotive.dto.UserDto;
import com.rjpinks.MarkwitzAutomotive.models.User;
import com.rjpinks.MarkwitzAutomotive.repository.UserRepository;
import com.rjpinks.MarkwitzAutomotive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAdmin(userDto.isAdmin());
        return userRepository.save(user);
    }
}

package com.rjpinks.MarkwitzAutomotive.service;

import com.rjpinks.MarkwitzAutomotive.dto.UserDto;
import com.rjpinks.MarkwitzAutomotive.models.User;

public interface UserService {
    User saveUser(UserDto userDto);
}

package com.rjpinks.MarkwitzAutomotive.repository;

import com.rjpinks.MarkwitzAutomotive.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

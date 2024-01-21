package com.rjpinks.MarkwitzAutomotive.repository;

import com.rjpinks.MarkwitzAutomotive.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}

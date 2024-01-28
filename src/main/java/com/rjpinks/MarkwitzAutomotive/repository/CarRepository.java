package com.rjpinks.MarkwitzAutomotive.repository;

import java.util.List;

import com.rjpinks.MarkwitzAutomotive.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByMake(String make);
    @Query("SELECT c FROM Car c WHERE c.model LIKE CONCAT('%', :query, '%')")
    List<Car> searchCars(String query);
}

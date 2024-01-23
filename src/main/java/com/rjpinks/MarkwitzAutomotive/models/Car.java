package com.rjpinks.MarkwitzAutomotive.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String vin;
    private String make;
    private String model;
    private int modelYear;
    private String carUrl;
    private long milage;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = true)
    private User user;
}

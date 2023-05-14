package com.HW1.task2.DTOs;

import com.HW1.task2.Entities.Engine;
import com.HW1.task2.Entities.Manufacturer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Engine engine;
    private Manufacturer manufacturer;
    private String brand;
    private int yearOfCreation;
    private double annualBudget;
}

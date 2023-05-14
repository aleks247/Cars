package com.HW1.task2.DTOs;

import com.HW1.task2.Entities.Engine;
import com.HW1.task2.Entities.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineDTO {
    private Manufacturer manufacturer;
    private String model;
    private int horsePower;
}

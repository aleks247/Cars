package com.HW1.task2.Controllers;

import com.HW1.task2.Entities.Car;
import com.HW1.task2.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@Controller
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/cars/all")
    public ResponseEntity<List<Car>> getAllManufacturers() {
        return carService.getAllCars();
    }

    @PostMapping("/cars/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car, UriComponentsBuilder uriComponentsBuilder) {
        return carService.addCar(car, uriComponentsBuilder);
    }
    @GetMapping("/getCarByManufacturerName/{name}")
    public ResponseEntity<List<Car>> getCarByManufacturerName(@PathVariable("name") String manufacturerName){
        return carService.getCarByManufacturerName(manufacturerName);
    }

    @GetMapping("/getCar/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") int id) {
        return carService.getCarById(id);
    }
    @GetMapping("/getCarByProcessorModel/{model}")
    public ResponseEntity<List<Car>> getCarByProcessorModel(@PathVariable("model") String processorModel){
        return carService.getCarByProcessorModel(processorModel);
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable("id") int id) {
        return carService.deleteCarById(id);
    }

    @PutMapping("/updateCar{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") int id, @RequestBody Car updatedCar) throws ChangeSetPersister.NotFoundException {
        return carService.updateCar(id, updatedCar);
    }
}

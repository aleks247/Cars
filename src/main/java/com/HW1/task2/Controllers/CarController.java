package com.HW1.task2.Controllers;

import com.HW1.task2.DTOs.CarDTO;
import com.HW1.task2.Entities.Car;
import com.HW1.task2.Services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAllCars();
        var status = cars.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(cars);
    }

    @PostMapping("/cars")
    public ResponseEntity<Void> addCar(@RequestBody Car car, UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/cars/{id}").buildAndExpand(carService.addCar(car).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getCar/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") int id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/getCarByManufacturerName/{name}")
    public ResponseEntity<List<CarDTO>> getCarByManufacturerName(@PathVariable("name") String manufacturerName) {
        List<CarDTO> cars = carService.getCarByManufacturerName(manufacturerName);
        var status = cars.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(cars);
    }

    @GetMapping("/getCarByProcessorModel/{model}")
    public ResponseEntity<List<CarDTO>> getCarByProcessorModel(@PathVariable("model") String processorModel) {
        List<CarDTO> cars = carService.getCarByProcessorModel(processorModel);
        var status = cars.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(cars);
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Car> deleteCarById(@PathVariable("id") int id) {
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateCar{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable(value = "id") int id, @RequestBody Car updatedCar) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(carService.updateCar(id, updatedCar));
    }
}
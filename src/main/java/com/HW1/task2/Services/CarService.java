package com.HW1.task2.Services;

import com.HW1.task2.Entities.Car;
import com.HW1.task2.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = (List<Car>) carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    public ResponseEntity<Car> addCar(Car car, UriComponentsBuilder uriComponentsBuilder) {
        Car savedCar = carRepository.save(car);
        URI location = uriComponentsBuilder.path("/cars/{id}").buildAndExpand(savedCar.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Car> getCarById(int id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<List<Car>> getCarByManufacturerName(String manufacturerName){
        List<Car> cars = carRepository.getCarByManufacturerName(manufacturerName);
        return ResponseEntity.ok(cars);
    }
    public ResponseEntity<List<Car>> getCarByProcessorModel(String model){
        List<Car> cars = carRepository.getCarByEngineModel(model);
        return ResponseEntity.ok(cars);
    }

    public ResponseEntity<Car> deleteCarById(int id) {
        carRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<Car> updateCar(int id, Car updatedCar) throws ChangeSetPersister.NotFoundException {
        Car carToUpdate = carRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        carToUpdate.setManufacturer(updatedCar.getManufacturer());
        carToUpdate.setBrand(updatedCar.getBrand());
        carToUpdate.setEngine(updatedCar.getEngine());
        carToUpdate.setManufacturer(updatedCar.getManufacturer());
        carToUpdate.setYearOfCreation(updatedCar.getYearOfCreation());
        Car savedCar = carRepository.save(carToUpdate);
        return ResponseEntity.ok(savedCar);
    }
}

package com.HW1.task2.Services;

import com.HW1.task2.DTOs.CarDTO;
import com.HW1.task2.Entities.Car;
import com.HW1.task2.Repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public List<CarDTO> getAllCars() {
        List<Car> cars = (List<Car>) carRepository.findAll();
        return cars.stream().map(lpt -> modelMapper.map(lpt, CarDTO.class)).collect(Collectors.toList());
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public CarDTO getCarById(int id) {
        Car car = carRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(car, CarDTO.class);
    }

    public List<CarDTO> getCarByManufacturerName(String manufacturerName) {
        List<Car> cars = carRepository.getCarByManufacturerName(manufacturerName);
        return cars.stream().map(lpt -> modelMapper.map(lpt, CarDTO.class)).collect(Collectors.toList());
    }

    public List<CarDTO> getCarByProcessorModel(String model) {
        List<Car> cars = carRepository.getCarByEngineModel(model);
        return cars.stream().map(lpt -> modelMapper.map(lpt, CarDTO.class)).collect(Collectors.toList());
    }

    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

    public CarDTO updateCar(int id, Car updatedCar) throws ChangeSetPersister.NotFoundException {
        Car carToUpdate = carRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        carToUpdate.setManufacturer(updatedCar.getManufacturer());
        carToUpdate.setBrand(updatedCar.getBrand());
        carToUpdate.setEngine(updatedCar.getEngine());
        carToUpdate.setManufacturer(updatedCar.getManufacturer());
        carToUpdate.setYearOfCreation(updatedCar.getYearOfCreation());
        carRepository.save(carToUpdate);
        return modelMapper.map(carToUpdate, CarDTO.class);
    }
}
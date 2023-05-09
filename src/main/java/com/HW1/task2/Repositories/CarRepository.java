package com.HW1.task2.Repositories;

import com.HW1.task2.Entities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    @Query("SELECT l FROM Car l WHERE l.manufacturer.name = :name")
    List<Car> getCarByManufacturerName(@Param("name") String name);
    @Query("SELECT l FROM Car l WHERE l.engine.model = :model")
    List<Car> getCarByEngineModel(@Param("model") String model);
}

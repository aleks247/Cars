package com.HW1.task2.Services;

import com.HW1.task2.Entities.Manufacturer;
import com.HW1.task2.Repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    ManufacturerRepository manufacturerRepository;

    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerRepository.findAll();
        return ResponseEntity.ok(manufacturers);
    }

    public ResponseEntity<Manufacturer> addManufacturer(Manufacturer manufacturer, UriComponentsBuilder uriComponentsBuilder) {
        Manufacturer savedManufacturers = manufacturerRepository.save(manufacturer);
        URI location = uriComponentsBuilder.path("/manufacturers/{id}").buildAndExpand(savedManufacturers.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Manufacturer> getManufacturerById(int id) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
        return manufacturer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Manufacturer> deleteManufacturerById(int id) {
        manufacturerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Manufacturer> updateManufacturer(int id, Manufacturer manufacturer) {
        Optional<Manufacturer> optionalManufacturer = manufacturerRepository.findById(id);
        if (optionalManufacturer.isPresent()) {
            Manufacturer newManufacturer = optionalManufacturer.get();
            if (manufacturer.getName() != null) {
                newManufacturer.setName(manufacturer.getName());
            }
            if (manufacturer.getYearOfCreation() != 0) {
                newManufacturer.setYearOfCreation(manufacturer.getYearOfCreation());
            }
            manufacturerRepository.save(newManufacturer);
            return ResponseEntity.ok(newManufacturer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

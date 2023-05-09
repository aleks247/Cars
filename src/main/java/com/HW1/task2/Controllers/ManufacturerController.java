package com.HW1.task2.Controllers;

import com.HW1.task2.Entities.Manufacturer;
import com.HW1.task2.Services.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/manufacturers/all")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @PostMapping("/manufacturers/add")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder uriComponentsBuilder) {
        return manufacturerService.addManufacturer(manufacturer, uriComponentsBuilder);
    }

    @GetMapping("/getManufacturer/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") int id) {
        return manufacturerService.getManufacturerById(id);
    }

    @DeleteMapping("/deleteManufacturer/{id}")
    public ResponseEntity<Manufacturer> deleteManufacturerById(@PathVariable("id") int id) {
        return manufacturerService.deleteManufacturerById(id);
    }

    @PutMapping("/updateManufacturer{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(int id, @RequestBody Manufacturer manufacturer) {
        return manufacturerService.updateManufacturer(id, manufacturer);
    }
}

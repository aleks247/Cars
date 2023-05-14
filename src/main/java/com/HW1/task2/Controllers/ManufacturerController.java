package com.HW1.task2.Controllers;

import com.HW1.task2.DTOs.ManufacturerDTO;
import com.HW1.task2.Entities.Manufacturer;
import com.HW1.task2.Services.ManufacturerService;
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
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping("/all")
    public ResponseEntity<List<ManufacturerDTO>> getAllManufacturers() {
        List<ManufacturerDTO> manufacturers = manufacturerService.getAllManufacturers();
        var status = manufacturers.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(manufacturers);
    }

    @PostMapping("/add")
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/manufacturers/{id}").buildAndExpand(manufacturerService.addManufacturer(manufacturer, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getManufacturer/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable("id") int id) {
        return ResponseEntity.ok(manufacturerService.getManufacturerById(id));
    }

    @DeleteMapping("/deleteManufacturer/{id}")
    public ResponseEntity<Manufacturer> deleteManufacturerById(@PathVariable("id") int id) {
        manufacturerService.deleteManufacturerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateManufacturer{id}")
    public ResponseEntity<ManufacturerDTO> updateManufacturer(int id, @RequestBody Manufacturer manufacturer) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(manufacturerService.updateManufacturer(id, manufacturer));
    }
}
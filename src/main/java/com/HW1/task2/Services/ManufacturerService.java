package com.HW1.task2.Services;

import com.HW1.task2.DTOs.ManufacturerDTO;
import com.HW1.task2.Entities.Manufacturer;
import com.HW1.task2.Repositories.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ModelMapper modelMapper;

    public List<ManufacturerDTO> getAllManufacturers() {
        List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerRepository.findAll();
        return manufacturers.stream().map(mnf -> modelMapper.map(mnf, ManufacturerDTO.class)).collect(Collectors.toList());
    }

    public Manufacturer addManufacturer(Manufacturer manufacturer, UriComponentsBuilder uriComponentsBuilder) {
        return manufacturerRepository.save(manufacturer);
    }

    public ManufacturerDTO getManufacturerById(int id) {
        Manufacturer manufacturer = manufacturerRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(manufacturer, ManufacturerDTO.class);
    }

    public void deleteManufacturerById(int id) {
        manufacturerRepository.deleteById(id);
    }

    public ManufacturerDTO updateManufacturer(int id, Manufacturer updatedManufacturer) throws ChangeSetPersister.NotFoundException {
        Manufacturer manufacturerUpdate = manufacturerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        manufacturerUpdate.setName(updatedManufacturer.getName());
        manufacturerUpdate.setYearOfCreation(updatedManufacturer.getYearOfCreation());
        manufacturerRepository.save(manufacturerUpdate);
        return modelMapper.map(manufacturerUpdate, ManufacturerDTO.class);
    }
}
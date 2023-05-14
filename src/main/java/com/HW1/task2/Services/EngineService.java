package com.HW1.task2.Services;

import com.HW1.task2.DTOs.EngineDTO;
import com.HW1.task2.Entities.Engine;
import com.HW1.task2.Repositories.EngineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;
    private final ModelMapper modelMapper;

    public List<EngineDTO> getAllEngines() {
        List<Engine> engines = (List<Engine>) engineRepository.findAll();
        return engines.stream().map(prc -> modelMapper.map(prc, EngineDTO.class)).collect(Collectors.toList());
    }

    public Engine addEngine(Engine engine, UriComponentsBuilder uriComponentsBuilder) {
        return engineRepository.save(engine);
    }

    public EngineDTO getEngineById(int id) {
        Optional<Engine> engine = engineRepository.findById(id);
        return modelMapper.map(engine, EngineDTO.class);
    }

    public void deleteEngineById(int id) {
        engineRepository.deleteById(id);
    }

    public EngineDTO updateEngine(int id, Engine updatedEngine) throws ChangeSetPersister.NotFoundException {
        Engine engineUpdate = engineRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        engineUpdate.setModel(updatedEngine.getModel());
        engineUpdate.setManufacturer(updatedEngine.getManufacturer());
        engineUpdate.setHorsePower(updatedEngine.getHorsePower());
        engineRepository.save(engineUpdate);
        return modelMapper.map(engineUpdate, EngineDTO.class);
    }
}
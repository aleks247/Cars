package com.HW1.task2.Services;

import com.HW1.task2.Entities.Engine;
import com.HW1.task2.Repositories.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@Service
public class EngineService {
    @Autowired
    EngineRepository engineRepository;

    public ResponseEntity<List<Engine>> getAllEngines() {
        List<Engine> engines = (List<Engine>) engineRepository.findAll();
        return ResponseEntity.ok(engines);
    }

    public ResponseEntity<Engine> addEngine(Engine engine, UriComponentsBuilder uriComponentsBuilder) {
        Engine savedEngine = engineRepository.save(engine);
        URI location = uriComponentsBuilder.path("/engines/{id}").buildAndExpand(savedEngine.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Engine> getEngineById(int id) {
        Optional<Engine> engine = engineRepository.findById(id);
        return engine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Engine> deleteEngineById(int id) {
        engineRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Engine> updateEngine(int id, Engine engine) {
        Optional<Engine> optionalEngine = engineRepository.findById(id);
        if (optionalEngine.isPresent()) {
            Engine newEngine = optionalEngine.get();
            if (engine.getHorsePower() != 0) {
                newEngine.setHorsePower(engine.getHorsePower());
            }
            if (engine.getModel() != null) {
                newEngine.setModel(engine.getModel());
            }
            if (engine.getManufacturer() != null) {
                newEngine.setManufacturer(engine.getManufacturer());
            }
            engineRepository.save(newEngine);
            return ResponseEntity.ok(newEngine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

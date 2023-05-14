package com.HW1.task2.Controllers;

import com.HW1.task2.DTOs.EngineDTO;
import com.HW1.task2.Entities.Engine;
import com.HW1.task2.Services.EngineService;
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
@RequestMapping("/engines")
public class EngineController {
    private final EngineService engineService;

    @GetMapping("/all")
    public ResponseEntity<List<EngineDTO>> getAllEngines(){
        List<EngineDTO> engines = engineService.getAllEngines();
        var status = engines.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(engines);
    }
    @PostMapping("/add")
    public ResponseEntity<Engine> addEngine(@RequestBody Engine engine, UriComponentsBuilder uriComponentsBuilder){
        URI location = uriComponentsBuilder.path("/engines/{id}").buildAndExpand(engineService.addEngine(engine, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/getEngine/{id}")
    public ResponseEntity<EngineDTO> getEngineById(@PathVariable("id") int id) {
        return ResponseEntity.ok(engineService.getEngineById(id));
    }
    @DeleteMapping("/deleteEngine/{id}")
    public ResponseEntity<Engine> deleteEngineById(@PathVariable("id") int id) {
        engineService.deleteEngineById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/updateEngine{id}")
    public ResponseEntity<EngineDTO> updateEngine (int id, @RequestBody Engine engine) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(engineService.updateEngine(id, engine));
    }
}
package com.HW1.task2.Controllers;

import com.HW1.task2.Entities.Engine;
import com.HW1.task2.Services.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class EngineController {
    @Autowired
    EngineService engineService;
    @GetMapping("/engines/all")
    public ResponseEntity<List<Engine>> getAllEngines(){
        return engineService.getAllEngines();
    }
    @PostMapping("engines/add")
    public ResponseEntity<Engine> addEngine(@RequestBody Engine engine, UriComponentsBuilder uriComponentsBuilder){
        return engineService.addEngine(engine, uriComponentsBuilder);
    }
    @GetMapping("/getEngine/{id}")
    public ResponseEntity<Engine> getEngineById(@PathVariable("id") int id) {
        return engineService.getEngineById(id);
    }
    @DeleteMapping("/deleteEngine/{id}")
    public ResponseEntity<Engine> deleteEngineById(@PathVariable("id") int id) {
        return engineService.deleteEngineById(id);
    }
    @PutMapping("/updateEngine{id}")
    public ResponseEntity<Engine> updateEngine (int id, @RequestBody Engine engine) {
        return engineService.updateEngine(id, engine);
    }
}

package com.HW1.task2.Controllers;

import com.HW1.task2.Entities.Worker;
import com.HW1.task2.Services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
public class WorkerController {
    @Autowired
    WorkerService workerService;
    @GetMapping("/workers/all")
    public ResponseEntity<List<Worker>> getAllWorkers(){
        return workerService.getAllWorkers();
    }
    @PostMapping("workers/add")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker, UriComponentsBuilder uriComponentsBuilder){
        return workerService.addWorker(worker, uriComponentsBuilder);
    }
    @GetMapping("/getWorker/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable("id") int id) {
        return workerService.getWorkerById(id);
    }
    @DeleteMapping("/deleteWorker/{id}")
    public ResponseEntity<Worker> deleteWorkerById(@PathVariable("id") int id) {
        return workerService.deleteWorkerById(id);
    }
    @PutMapping("/updateWorker{id}")
    public ResponseEntity<Worker> updateWorker (int id, @RequestBody Worker worker) {
        return workerService.updateAlreadyExistingWorker(id, worker);
    }
}
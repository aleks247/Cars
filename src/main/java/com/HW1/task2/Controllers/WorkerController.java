package com.HW1.task2.Controllers;

import com.HW1.task2.DTOs.WorkerDTO;
import com.HW1.task2.Entities.Worker;
import com.HW1.task2.Services.WorkerService;
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
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerService workerService;

    @GetMapping("/all")
    public ResponseEntity<List<WorkerDTO>> getAllWorkers() {
        List<WorkerDTO> workers = workerService.getAllWorkers();
        var status = workers.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(workers);
    }

    @PostMapping("/add")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker, UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/workers/{id}").buildAndExpand(workerService.addWorker(worker, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getWorker/{id}")
    public ResponseEntity<WorkerDTO> getWorkerById(@PathVariable("id") int id) {
        return ResponseEntity.ok(workerService.getWorkerById(id));
    }

    @DeleteMapping("/deleteWorker/{id}")
    public ResponseEntity<Worker> deleteWorkerById(@PathVariable("id") int id) {
        workerService.deleteWorkerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateWorker{id}")
    public ResponseEntity<WorkerDTO> updateWorker(int id, @RequestBody Worker worker) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(workerService.updateWorker(id, worker));
    }
}
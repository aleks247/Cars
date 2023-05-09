package com.HW1.task2.Services;

import com.HW1.task2.Entities.Worker;
import com.HW1.task2.Repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;

    public ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = (List<Worker>) workerRepository.findAll();
        return ResponseEntity.ok(workers);
    }

    public ResponseEntity<Worker> addWorker(Worker worker, UriComponentsBuilder uriComponentsBuilder) {
        Worker savedWorker = workerRepository.save(worker);
        URI location = uriComponentsBuilder.path("/workers/{id}").buildAndExpand(savedWorker.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Worker> getWorkerById(int id) {
        Optional<Worker> worker = workerRepository.findById(id);
        return worker.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Worker> deleteWorkerById(int id) {
        workerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Worker> updateWorker(int id, Worker worker) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()) {
            Worker newWorker = optionalWorker.get();
            if (worker.getName() != null) {
                newWorker.setName(worker.getName());
            }
            if (worker.getAge() != 0) {
                newWorker.setAge(worker.getAge());
                //Creating merge conflict
                //Creating merge conflict 2
            }
            if (worker.getPosition() != null) {
                newWorker.setPosition(worker.getPosition());
            }
            workerRepository.save(newWorker);
            return ResponseEntity.ok(newWorker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package com.HW1.task2.Services;

import com.HW1.task2.DTOs.WorkerDTO;
import com.HW1.task2.Entities.Worker;
import com.HW1.task2.Repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final ModelMapper modelMapper;

    public List<WorkerDTO> getAllWorkers() {
        List<Worker> workers = (List<Worker>) workerRepository.findAll();
        return workers.stream().map(mnf -> modelMapper.map(mnf, WorkerDTO.class)).collect(Collectors.toList());
    }

    public Worker addWorker(Worker worker, UriComponentsBuilder uriComponentsBuilder) {
        return workerRepository.save(worker);
    }

    public WorkerDTO getWorkerById(int id) {
        Worker worker = workerRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(worker, WorkerDTO.class);
    }

    public void deleteWorkerById(int id) {
        workerRepository.deleteById(id);
    }

    public WorkerDTO updateWorker(int id, Worker updatedWorker) throws ChangeSetPersister.NotFoundException {
        Worker workerUpdate = workerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        workerUpdate.setName(updatedWorker.getName());
        workerUpdate.setAge(updatedWorker.getAge());
        workerUpdate.setRole(updatedWorker.getRole());
        workerRepository.save(workerUpdate);
        return modelMapper.map(workerUpdate, WorkerDTO.class);
    }
}
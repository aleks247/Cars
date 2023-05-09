package com.HW1.task2.Repositories;

import com.HW1.task2.Entities.Worker;
import org.springframework.data.repository.CrudRepository;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
}

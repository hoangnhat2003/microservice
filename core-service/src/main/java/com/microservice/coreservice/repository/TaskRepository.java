package com.microservice.coreservice.repository;

import com.microservice.coreservice.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}

package com.microservice.coreservice.repository;

import com.microservice.coreservice.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    boolean existsByName(String name);
}

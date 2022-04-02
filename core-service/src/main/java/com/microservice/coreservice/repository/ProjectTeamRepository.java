package com.microservice.coreservice.repository;

import com.microservice.coreservice.entity.ProjectTeam;
import org.springframework.data.repository.CrudRepository;

public interface ProjectTeamRepository extends CrudRepository<ProjectTeam, Integer> {
}

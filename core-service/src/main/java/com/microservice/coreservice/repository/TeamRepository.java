package com.microservice.coreservice.repository;

import com.microservice.coreservice.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}

package com.microservice.coreservice.repository;

import com.microservice.coreservice.entity.TeamUser;
import org.springframework.data.repository.CrudRepository;

public interface TeamUserRepository extends CrudRepository<TeamUser, Integer> {
}

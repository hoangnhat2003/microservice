package com.microservice.coreservice.controller;

import com.microservice.coreservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;


}

package com.microservice.coreservice.service;

import com.microservice.coreservice.domain.form.ProjectForm;
import com.microservice.coreservice.entity.Project;

public interface ProjectService {

    Project addNewProject(ProjectForm form);

    Project update(ProjectForm form, int projectId);

}

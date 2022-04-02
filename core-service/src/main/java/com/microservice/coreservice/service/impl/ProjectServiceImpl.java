package com.microservice.coreservice.service.impl;

import com.microservice.coreservice.constants.ProjectEnums;
import com.microservice.coreservice.domain.form.ProjectForm;
import com.microservice.coreservice.entity.Project;
import com.microservice.coreservice.entity.ProjectTeam;
import com.microservice.coreservice.repository.ProjectRepository;
import com.microservice.coreservice.repository.ProjectTeamRepository;
import com.microservice.coreservice.service.ProjectService;
import com.microservice.coreservice.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectTeamRepository projectTeamRepository;



    @Override
    public Project addNewProject(ProjectForm form) {
        validateProjectForm(form);

        Project project = Project.builder()
                .name(form.getName())
                .description(form.getDescription())
                .enabled(Boolean.TRUE)
                .created_date(new Timestamp(System.currentTimeMillis()))
                .type(ProjectEnums.valueOf(form.getType()))
                .build();
        projectRepository.save(project);

        ProjectTeam projectTeam = ProjectTeam.builder()
                .project_id(project.getId())
                .team_id(form.getTeamId())
                .created_date(new Timestamp(System.currentTimeMillis()))
                .build();

        projectTeamRepository.save(projectTeam);
        return project;
    }

    @Override
    public Project update(ProjectForm form, int projectId) {
        validateUpdateProject(form, projectId);
        Project project = projectRepository.findById(projectId).get();
        project.setName(form.getName());
        project.setDescription(form.getDescription());
        project.setType(ProjectEnums.valueOf(form.getType()));

    }


    private void validateProjectForm(ProjectForm form) {
        Map<String, String> map = new HashMap<>();
        map.put("name", form.getName());
        map.put("decription", form.getDescription());
        ValidateUtils.validateNullOrBlankString((HashMap<String, String>) map);

        if(projectRepository.existsByName(form.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tên dự án đã tồn tại");
        }

    }

    private void validateUpdateProject(ProjectForm form, int projectId) {
        Map<String, String> map = new HashMap<>();
        map.put("name", form.getName());
        map.put("decription", form.getDescription());
        ValidateUtils.validateNullOrBlankString((HashMap<String, String>) map);

        if(projectRepository.existsByName(form.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tên dự án đã tồn tại");
        }

        if(projectRepository.existsById(projectId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không tìm thấy dự án");
        }

    }


}

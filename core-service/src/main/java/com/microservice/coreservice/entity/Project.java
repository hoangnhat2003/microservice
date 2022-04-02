package com.microservice.coreservice.entity;

import com.microservice.coreservice.constants.ProjectEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pa_projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private ProjectEnums type;

    @Column
    private Boolean enabled;

    @Column
    private Timestamp created_date;

    @Column
    private Timestamp updated_date;



}

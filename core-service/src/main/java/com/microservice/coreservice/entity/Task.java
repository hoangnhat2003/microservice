package com.microservice.coreservice.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.coreservice.constants.PriorityEnums;
import com.microservice.coreservice.constants.StatusEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pa_tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int created_by;

    @Column
    private int assignee;

    @Column
    private int project_id;

    @Column
    private int section_id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private PriorityEnums priority;

    @Column
    private StatusEnums status;

    @Column
    private float estimate_time;

    @Column
    private Timestamp start_date;

    @Column
    private Timestamp end_date;

    @Column
    private int parent_id;

    @Column
    private Timestamp created_date;

    @Column
    private Timestamp updated_date;




















}

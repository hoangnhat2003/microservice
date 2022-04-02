package com.microservice.coreservice.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pa_project_teams")
@IdClass(ProjectTeam.class)
public class ProjectTeam implements Serializable {

    @Id
    private int project_id;

    @Id
    private int team_id;

    @Column
    private Timestamp created_date;

    @Column
    private Timestamp updated_date;



}

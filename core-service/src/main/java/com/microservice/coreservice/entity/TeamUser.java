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
@Table(name = "pa_team_users")
@IdClass(TeamUser.class)
public class TeamUser implements Serializable {

    @Id
    private int team_id;

    @Column
    private int department_id;

    @Id
    private int user_id;

    @Column
    private Timestamp created_date;

    @Column
    private Timestamp updated_date;


}

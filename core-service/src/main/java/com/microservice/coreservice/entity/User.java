package com.microservice.coreservice.entity;
import com.microservice.coreservice.constants.PriorityEnums;
import com.microservice.coreservice.constants.ProjectEnums;
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
@Table(name = "pa_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private int department_id;

    @Column
    private Timestamp created_date;

    @Column
    private Timestamp updated_date;
}

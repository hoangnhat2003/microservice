package com.microservice.coreservice.domain.form;

import lombok.Data;

@Data
public class UserForm {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int department_id;
}

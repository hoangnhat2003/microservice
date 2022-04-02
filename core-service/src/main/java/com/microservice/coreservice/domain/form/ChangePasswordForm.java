package com.microservice.coreservice.domain.form;

import lombok.Data;

@Data
public class ChangePasswordForm {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}

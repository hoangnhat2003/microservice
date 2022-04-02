package com.microservice.coreservice.service;

import com.microservice.coreservice.domain.form.ChangePasswordForm;
import com.microservice.coreservice.domain.form.UserForm;
import com.microservice.coreservice.entity.User;

import java.util.List;

public interface UserService {

    User createNewUser(UserForm userForm);

    void changePassword(ChangePasswordForm changePasswordForm, int userId);

    User findUserById(int userId);

    List<User> getListUsers();

    void deleteUser(int userId);
}

package com.microservice.coreservice.service.impl;

import com.microservice.coreservice.domain.form.ChangePasswordForm;
import com.microservice.coreservice.domain.form.UserForm;
import com.microservice.coreservice.entity.Department;
import com.microservice.coreservice.entity.User;
import com.microservice.coreservice.repository.UserRepository;
import com.microservice.coreservice.service.UserService;
import com.microservice.coreservice.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createNewUser(UserForm userForm) {

        validateUserForm(userForm);

        User user = User.builder()
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .phone(userForm.getPhone())
                .department_id(userForm.getDepartment_id())
                .address(userForm.getAddress())
                .username(userForm.getUsername())
                .build();

        user.setCreated_date(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }

    @Override
    public void changePassword( ChangePasswordForm changePasswordForm, int userId) {

        User currentAccount = userRepository.findById(userId).get();
        if(ObjectUtils.isEmpty(currentAccount)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Permission!");
        }

        validateChangePasswordDto(changePasswordForm);

        if(!passwordEncoder.matches(currentAccount.getPassword(), changePasswordForm.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mật khẩu không đúng!");
        }
        if(!Objects.equals(changePasswordForm.getNewPassword(), changePasswordForm.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Xác nhận mật khẩu không trùng khớp nhau!");
        }

        currentAccount.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        currentAccount.setUpdated_date(new Timestamp(System.currentTimeMillis()));
        userRepository.save(currentAccount);
    }

    @Override
    public User findUserById(int userId) {
        User user = userRepository.findById(userId).get();

        if(ObjectUtils.isEmpty(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Không tìm thấy user");
        }

        return user;
    }

    @Override
    public List<User> getListUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if(CollectionUtils.isEmpty(users)) {
            return Collections.EMPTY_LIST;
        }

        return users;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).get();

        if(ObjectUtils.isEmpty(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Không tìm thấy user");
        }
        userRepository.deleteById(userId);
    }

    private void validateChangePasswordDto(ChangePasswordForm changePasswordForm) {

        HashMap<String, String> map = new HashMap<>();
        map.put("mật khẩu cũ", changePasswordForm.getOldPassword());
        map.put("mật khẩu mới", changePasswordForm.getNewPassword());
        map.put("mật khẩu xác nhận", changePasswordForm.getConfirmPassword());
        ValidateUtils.validateNullOrBlankString(map);
    }

    private void validateUserForm(UserForm userForm) {
        Map<String,String> map = new HashMap<>();
        map.put("username", userForm.getUsername());
        map.put("password", userForm.getPassword());
        map.put("email", userForm.getEmail());

        ValidateUtils.validateNullOrBlankString((HashMap<String, String>) map);
        ValidateUtils.isEmail(userForm.getEmail());
        ValidateUtils.isPhoneNumber(userForm.getPhone());
        ValidateUtils.isPassword(userForm.getPassword());

        if(userRepository.existsByEmail(userForm.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email đã tồn tại!");
        }

        if(userRepository.existsByUsername(userForm.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username đã tồn tại!");
        }

        if(userRepository.existsByPhone(userForm.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SDT đã tồn tại!");
        }

//        Department department = restTemplate.getForObject(String.format("http://DEPARTMENT-SERVICE/departments/%s", userForm.getDepartment_id()), Department.class);
//
//        if(ObjectUtils.isEmpty(department)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department không tồn tại!");
//        }
    }
}

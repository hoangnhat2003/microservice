package com.microservice.coreservice.controller;

import com.microservice.coreservice.domain.dto.UserDto;
import com.microservice.coreservice.domain.form.ChangePasswordForm;
import com.microservice.coreservice.domain.form.UserForm;
import com.microservice.coreservice.entity.User;
import com.microservice.coreservice.service.UserService;
import com.microservice.coreservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse> createNewUser(@RequestBody UserForm _form) {

        User user = userService.createNewUser(_form);
        UserDto data = UserDto.toDto(user);
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.CREATED.value(), "Thêm mới người dùng thành công")
                : ApiResponse.appendError(HttpStatus.NO_CONTENT.value(), "Thêm mới người dùng thất bại");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordForm _form, @PathVariable(name = "id") int _userId) {

        userService.changePassword(_form, _userId);
        ApiResponse response = ApiResponse.appendSuccess(null, HttpStatus.OK.value(), "Đổi mật khẩu thành công");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findUserById(@PathVariable(name = "id") int _userId) {

        User user = userService.findUserById(_userId);
        UserDto data = UserDto.toDto(user);
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), null)
                : ApiResponse.appendError(HttpStatus.NO_CONTENT.value(), null);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse> getUserLists() {

        List<User> users = userService.getListUsers();
        List<UserDto> data = users.stream().map(UserDto::new).collect(Collectors.toList());
        ApiResponse response = data != null ? ApiResponse.appendSuccess(data, HttpStatus.OK.value(), null)
                : ApiResponse.appendError(HttpStatus.NO_CONTENT.value(), null);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") int _userId) {

        userService.deleteUser(_userId);
        ApiResponse response = ApiResponse.appendSuccess(null, HttpStatus.OK.value(), "Xóa người dùng thành công");
        return ResponseEntity.ok(response);
    }
}

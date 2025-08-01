package com.userp1.controller;

import com.userp1.dto.UserRequest;
import com.userp1.dto.UserResponse;
import com.userp1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CRUD - "C (Create)"  => 생성
    @PostMapping("/users")
    public UserResponse createUser(
            @RequestBody UserRequest userRequest
    ){
        return userService.save(userRequest);
    }

    // CRUD - "R (Read)"  => 전체 조회
    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.findUsers();
    }

    // CRUD - "R (Read)"  => 단건 조회
    @GetMapping("/users/{userId}")
    public UserResponse getUser(
            @PathVariable Long userId
    ){
        return userService.findUser(userId);
    }

    // CRUD - "U (Update)"  => 수정
    @PutMapping("/users/{userId}")
    public UserResponse updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ){
        return userService.update(userId, userRequest);
    }

    // CRUD - "D (Delete)"  => 삭제
    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ){
        userService.deleteUser(userId);
    }
}

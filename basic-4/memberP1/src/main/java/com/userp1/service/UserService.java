package com.userp1.service;

import com.userp1.dto.UserRequest;
import com.userp1.dto.UserResponse;
import com.userp1.entity.User;
import com.userp1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // CRUD - "C (Create)"  => 생성
    @Transactional
    public UserResponse save(UserRequest userRequest) {
        User savedUser = userRepository.save(new User(userRequest.getUsername()));

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername()
        );
    }

    // CRUD - "R (Read)"  => 전체 조회
    @Transactional(readOnly = true)
    public List<UserResponse> findUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();

        for (User user : users) {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getUsername()
            );
            dtos.add(userResponse);
        }
        return dtos;
    }

    // CRUD - "U (Update)"  => 수정
    @Transactional
    public UserResponse update(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );
        user.updateUsername(userRequest.getUsername());
        return new UserResponse(
                user.getId(),
                user.getUsername()
        );
    }

    // CRUD - "D (Delete)"  => 삭제
    @Transactional
    public void deleteUser(Long userId) {
        boolean b = userRepository.existsById(userId);
        if (!b) {
            throw new IllegalArgumentException(("해당하는 userId가 없습니다."));
        }
        userRepository.deleteById(userId);
    }

    // CRUD - "R (Read)"  => 단건 조회
    @Transactional(readOnly = true)
    public UserResponse findUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 userId가 없습니다.")
        );

        return new UserResponse(user.getId(), user.getUsername());
    }

}

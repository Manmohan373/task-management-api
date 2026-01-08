package com.example.task_manager.controller;

import com.example.task_manager.dto.UserRequestDTO;
import com.example.task_manager.dto.UserResponseDTO;
import com.example.task_manager.entity.User;
import com.example.task_manager.service.UserService;
import com.example.task_manager.util.MapperUtil;
import com.example.task_manager.util.ServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ServiceResponse<UserResponseDTO>> createUser(
            @Valid @RequestBody UserRequestDTO request) {

        User user = userService.createUser(
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServiceResponse.success(
                        "User created successfully",
                        MapperUtil.toUserResponse(user)
                ));
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<UserResponseDTO>>> getAllUsers(Pageable pageable) {

        List<UserResponseDTO> users = userService.getAllUsers(pageable)
                .stream()
                .map(MapperUtil::toUserResponse)
                .toList();

        return ResponseEntity.ok(
                ServiceResponse.success("Users fetched", users)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<UserResponseDTO>> getUser(@PathVariable Long id) {

        return ResponseEntity.ok(
                ServiceResponse.success(
                        "User fetched",
                        MapperUtil.toUserResponse(userService.getUserById(id))
                )
        );
    }
}

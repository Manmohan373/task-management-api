package com.example.task_manager.controller;

import com.example.task_manager.dto.LoginRequest;
import com.example.task_manager.security.JwtUtil;
import com.example.task_manager.util.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ServiceResponse<String>> login(
            @RequestBody LoginRequest request) {

        if (!"admin".equals(request.getUsername())
                || !"password".equals(request.getPassword())) {

            return ResponseEntity.status(401)
                    .body(ServiceResponse.failure("Invalid credentials"));
        }

        String token = jwtUtil.generateToken(request.getUsername());

        return ResponseEntity.ok(
                ServiceResponse.success("Login successful", token)
        );
    }

}


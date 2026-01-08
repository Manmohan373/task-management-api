package com.example.task_manager.service;

import com.example.task_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(User user);

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long id);
}

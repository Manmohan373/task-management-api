package com.example.task_manager.util;

import com.example.task_manager.dto.TaskResponseDTO;
import com.example.task_manager.dto.UserResponseDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.User;

public final class MapperUtil {

    private MapperUtil() {}

    public static UserResponseDTO toUserResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static TaskResponseDTO toTaskResponse(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .assignedUserId(
                        task.getAssignedTo() != null ? task.getAssignedTo().getId() : null
                )
                .build();
    }
}


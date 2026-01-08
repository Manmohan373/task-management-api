package com.example.task_manager.service;

import com.example.task_manager.dto.TaskRequestDTO;
import com.example.task_manager.dto.TaskResponseDTO;
import com.example.task_manager.util.Priority;
import com.example.task_manager.util.TaskStatus;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO request);

    List<TaskResponseDTO> getTasks(
            TaskStatus status,
            Priority priority,
            Long assignedUserId,
            Pageable pageable
    );

    TaskResponseDTO getTaskById(Long id);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO request);

    TaskResponseDTO updateTaskStatus(Long id, TaskStatus status);

    void deleteTask(Long id);
}


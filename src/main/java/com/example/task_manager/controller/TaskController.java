package com.example.task_manager.controller;

import com.example.task_manager.dto.TaskRequestDTO;
import com.example.task_manager.dto.TaskResponseDTO;
import com.example.task_manager.dto.TaskStatusUpdateDTO;
import com.example.task_manager.service.TaskService;
import com.example.task_manager.util.Priority;
import com.example.task_manager.util.ServiceResponse;
import com.example.task_manager.util.TaskStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ServiceResponse<TaskResponseDTO>> createTask(
            @Valid @RequestBody TaskRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ServiceResponse.success(
                        "Task created successfully",
                        taskService.createTask(request)
                ));
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<TaskResponseDTO>>> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Long assignedUserId,
            Pageable pageable) {

        return ResponseEntity.ok(
                ServiceResponse.success(
                        "Tasks fetched successfully",
                        taskService.getTasks(status, priority, assignedUserId, pageable)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<TaskResponseDTO>> getTaskById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ServiceResponse.success(
                        "Task fetched successfully",
                        taskService.getTaskById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<TaskResponseDTO>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO request) {

        return ResponseEntity.ok(
                ServiceResponse.success(
                        "Task updated successfully",
                        taskService.updateTask(id, request)
                )
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ServiceResponse<TaskResponseDTO>> updateTaskStatus(
            @PathVariable Long id,
            @Valid @RequestBody TaskStatusUpdateDTO request) {

        return ResponseEntity.ok(
                ServiceResponse.success(
                        "Task status updated successfully",
                        taskService.updateTaskStatus(id, request.getStatus())
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.ok(
                ServiceResponse.success("Task deleted successfully", null)
        );
    }
}



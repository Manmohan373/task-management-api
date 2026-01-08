package com.example.task_manager.dto;

import com.example.task_manager.util.Priority;
import com.example.task_manager.util.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be 3–100 characters")
    private String title;

    @Size(max = 500, message = "Description can be max 500 characters")
    private String description;

    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
    private Long assignedUserId;
}

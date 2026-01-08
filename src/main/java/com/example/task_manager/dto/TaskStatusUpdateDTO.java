package com.example.task_manager.dto;

import com.example.task_manager.util.TaskStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateDTO {
    @NotNull
    private TaskStatus status;
}

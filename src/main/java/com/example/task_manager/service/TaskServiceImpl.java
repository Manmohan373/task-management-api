package com.example.task_manager.service;

import com.example.task_manager.dto.TaskRequestDTO;
import com.example.task_manager.dto.TaskResponseDTO;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.User;
import com.example.task_manager.exception.ResourceNotFoundException;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.repository.UserRepository;
import com.example.task_manager.repository.specification.TaskSpec;
import com.example.task_manager.util.MapperUtil;
import com.example.task_manager.util.Priority;
import com.example.task_manager.util.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(
                request.getStatus() != null ? request.getStatus() : TaskStatus.TODO
        );
        task.setPriority(
                request.getPriority() != null ? request.getPriority() : Priority.MEDIUM
        );
        task.setDueDate(request.getDueDate());

        if (request.getAssignedUserId() != null) {
            User user = userRepository.findById(request.getAssignedUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User not found with id: " + request.getAssignedUserId()
                            ));
            task.setAssignedTo(user);
        }

        return MapperUtil.toTaskResponse(taskRepository.save(task));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasks(
            TaskStatus status,
            Priority priority,
            Long assignedUserId,
            Pageable pageable) {

        Specification<Task> spec = Specification
                .where(TaskSpec.hasStatus(status))
                .and(TaskSpec.hasPriority(priority))
                .and(TaskSpec.hasAssignedUser(assignedUserId));

        return taskRepository.findAll(spec, pageable)
                .stream()
                .map(MapperUtil::toTaskResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDTO getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));

        return MapperUtil.toTaskResponse(task);
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        if (request.getAssignedUserId() != null) {
            User user = userRepository.findById(request.getAssignedUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User not found with id: " + request.getAssignedUserId()
                            ));
            task.setAssignedTo(user);
        } else {
            task.setAssignedTo(null);
        }

        return MapperUtil.toTaskResponse(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO updateTaskStatus(Long id, TaskStatus status) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));

        task.setStatus(status);

        return MapperUtil.toTaskResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
    }
}

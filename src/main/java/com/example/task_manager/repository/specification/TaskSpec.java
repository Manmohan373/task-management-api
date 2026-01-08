package com.example.task_manager.repository.specification;

import com.example.task_manager.entity.Task;
import com.example.task_manager.util.Priority;
import com.example.task_manager.util.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public final class TaskSpec {

    private TaskSpec() {}

    public static Specification<Task> hasStatus(TaskStatus status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Task> hasPriority(Priority priority) {
        return (root, query, cb) ->
                priority == null ? null : cb.equal(root.get("priority"), priority);
    }

    public static Specification<Task> hasAssignedUser(Long userId) {
        return (root, query, cb) ->
                userId == null ? null :
                        cb.equal(root.get("assignedTo").get("id"), userId);
    }
}

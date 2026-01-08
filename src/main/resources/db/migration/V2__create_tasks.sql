CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(20) NOT NULL,
    due_date DATE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    assigned_to_user_id BIGINT,
    CONSTRAINT fk_tasks_assigned_user
        FOREIGN KEY (assigned_to_user_id)
        REFERENCES users (id)
        ON DELETE SET NULL
);

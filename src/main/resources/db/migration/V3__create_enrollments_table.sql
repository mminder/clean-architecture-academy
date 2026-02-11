CREATE TABLE enrollments (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_enrollments_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE enrollments (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    user_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    course_run_id UUID NOT NULL,
    CONSTRAINT fk_enrollments_user
        FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_enrollments_course_run
        FOREIGN KEY (course_run_id) REFERENCES course_runs(id)
);

CREATE TABLE course_runs (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    max_participants INTEGER NOT NULL,
    trainer UUID NOT NULL,
    CONSTRAINT fk_course_runs_trainer
        FOREIGN KEY (trainer) REFERENCES trainers(id)
);

CREATE TABLE lessons (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    course_run_id UUID NOT NULL,
    description VARCHAR(1024) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_lessons_course_run
        FOREIGN KEY (course_run_id) REFERENCES course_runs(id)
);

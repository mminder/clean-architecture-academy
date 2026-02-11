CREATE TABLE courses (
    id UUID PRIMARY KEY,
    version BIGINT NOT NULL DEFAULT 0,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE course_course_runs (
    course_id UUID NOT NULL,
    course_run_id UUID NOT NULL,
    PRIMARY KEY (course_id, course_run_id),
    CONSTRAINT fk_course_course_runs_course
        FOREIGN KEY (course_id) REFERENCES courses(id),
    CONSTRAINT fk_course_course_runs_course_run
        FOREIGN KEY (course_run_id) REFERENCES course_runs(id)
);

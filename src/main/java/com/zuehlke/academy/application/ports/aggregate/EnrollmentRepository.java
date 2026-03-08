package com.zuehlke.academy.application.ports.aggregate;

import com.zuehlke.academy.domain.Enrollment;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository {
    List<Enrollment> findAllByUser(UUID userId);

    void create(Enrollment enrollment);

    List<Enrollment> findAllByCourseRun(UUID courseRunId);
}

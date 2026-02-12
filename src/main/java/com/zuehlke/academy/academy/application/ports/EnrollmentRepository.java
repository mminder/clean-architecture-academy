package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.domain.Enrollment;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository {
    List<Enrollment> findAllEnrollmentsForUser(UUID userId);
}

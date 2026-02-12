package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.domain.Enrollment;

import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository {
    List<Enrollment> findAllEnrollmentsForUser(UUID userId);
}

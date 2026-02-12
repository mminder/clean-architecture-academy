package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.academy.application.dto.UserCourseRunEnrollmentResponse;
import com.zuehlke.academy.academy.domain.Enrollment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Loads an overview for a given user, listing all his enrollments.
 */
@Service
public class LoadUserCourseRunEnrollments {

    private final EnrollmentRepository enrollmentRepository;

    public LoadUserCourseRunEnrollments(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<UserCourseRunEnrollmentResponse> execute(UUID userId) {
        List<Enrollment> enrollments = this.enrollmentRepository.findAllEnrollmentsForUser(userId);
        return enrollments.stream()
                .map(enrollment -> new UserCourseRunEnrollmentResponse(
                        enrollment.id(),
                        enrollment.courseRunId(),
                        enrollment.userId(),
                        enrollment.status(),
                        enrollment.createdAt()
                ))
                .toList();
    }
}

package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.academy.application.readmodel.UserCourseRunEnrollmentReadModel;
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

    public List<UserCourseRunEnrollmentReadModel> execute(UUID userId) {
        return this.enrollmentRepository.findAllCourseRunEnrollmentsForUser(userId);
    }
}

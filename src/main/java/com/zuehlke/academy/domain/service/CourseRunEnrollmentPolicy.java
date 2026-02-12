package com.zuehlke.academy.domain.service;

import com.zuehlke.academy.domain.CourseRun;
import com.zuehlke.academy.domain.Enrollment;
import com.zuehlke.academy.shared.exception.ApplicationException;

import java.util.List;

public class CourseRunEnrollmentPolicy {

    public static void validateEnrollmentAllowed(CourseRun courseRun, List<Enrollment> existingEnrollments) {
        long confirmedEnrollmentsCount = existingEnrollments.stream()
                .filter(Enrollment::isConfirmed)
                .count();

        if (confirmedEnrollmentsCount >= courseRun.maxParticipants()) {
            throw new ApplicationException(
                    String.format("Course run is full. Maximum participants: %d, Current enrollments: %d",
                            courseRun.maxParticipants(), confirmedEnrollmentsCount)
            );
        }
    }
}

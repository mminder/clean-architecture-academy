package com.zuehlke.academy.domain.service;

import com.zuehlke.academy.domain.courseRun.CourseRun;
import com.zuehlke.academy.domain.Enrollment;
import com.zuehlke.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRunEnrollmentPolicy {

    public void validateEnrollmentAllowed(CourseRun courseRun, List<Enrollment> existingEnrollments) {
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

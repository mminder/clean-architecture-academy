package com.zuehlke.academy.application;

import com.zuehlke.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.application.ports.UserRepository;
import com.zuehlke.academy.domain.CourseRun;
import com.zuehlke.academy.domain.Enrollment;
import com.zuehlke.academy.domain.service.CourseRunEnrollmentPolicy;
import com.zuehlke.academy.domain.User;
import com.zuehlke.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnrollUserInCourseRun {

    private final UserRepository userRepository;
    private final CourseRunRepository courseRunRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRunEnrollmentPolicy courseRunEnrollmentPolicy;

    public EnrollUserInCourseRun(UserRepository userRepository,
                                 CourseRunRepository courseRunRepository,
                                 EnrollmentRepository enrollmentRepository,
                                 CourseRunEnrollmentPolicy courseRunEnrollmentPolicy) {
        this.userRepository = userRepository;
        this.courseRunRepository = courseRunRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseRunEnrollmentPolicy = courseRunEnrollmentPolicy;
    }

    public void execute(UUID courseRunId, UUID userId) {
        User user = this.userRepository.findById(userId);
        if (!user.isStudent()) {
            throw new ApplicationException("Only students can enroll in course runs.");
        }

        CourseRun courseRun = this.courseRunRepository.findById(courseRunId);
        List<Enrollment> courseEnrollments = this.enrollmentRepository.findAllForCourseRun(courseRunId);
        courseRunEnrollmentPolicy.validateEnrollmentAllowed(courseRun, courseEnrollments);

        this.enrollmentRepository.create(Enrollment.create(userId, courseRunId));
    }
}

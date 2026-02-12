package com.zuehlke.academy.application;

import com.zuehlke.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.application.ports.UserRepository;
import com.zuehlke.academy.domain.courseRun.CourseRunAggregate;
import com.zuehlke.academy.domain.User;
import com.zuehlke.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnrollUserInCourseRun {

    private final UserRepository userRepository;
    private final CourseRunRepository courseRunRepository;

    public EnrollUserInCourseRun(UserRepository userRepository, CourseRunRepository courseRunRepository, EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.courseRunRepository = courseRunRepository;
    }

    public void execute(UUID courseRunId, UUID userId) {
        User user = this.userRepository.findById(userId);
        if (!user.isStudent()) {
            throw new ApplicationException("Only students can enroll in course runs.");
        }

        // TODO: evaluate usage of more narrow CourseRun that doesn't implement nested enrollments "automatically"
        // Specific CourseRunEnrollmentContext could be used to simply create/validate the enrollment. Saving would then use EnrollmentRepository.
        CourseRunAggregate courseRunAggregate = this.courseRunRepository.findById(courseRunId);
        courseRunAggregate.enrollUser(user.id());
        this.courseRunRepository.update(courseRunAggregate);
    }
}

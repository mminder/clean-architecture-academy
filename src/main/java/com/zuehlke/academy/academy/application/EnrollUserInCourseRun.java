package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.academy.application.ports.UserRepository;
import com.zuehlke.academy.academy.domain.CourseRun;
import com.zuehlke.academy.academy.domain.User;
import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnrollUserInCourseRun {

    private final UserRepository userRepository;
    private final CourseRunRepository courseRunRepository;

    public EnrollUserInCourseRun(UserRepository userRepository, CourseRunRepository courseRunRepository) {
        this.userRepository = userRepository;
        this.courseRunRepository = courseRunRepository;
    }

    public void execute(UUID courseRunId, UUID userId) {
        User user = this.userRepository.findById(userId);
        if(!user.isStudent()){
            throw new ApplicationException("Only students can enroll in course runs.");
        }

        CourseRun courseRun = this.courseRunRepository.findById(courseRunId);
        courseRun.enrollUser(user.id());
        this.courseRunRepository.update(courseRun);
    }
}

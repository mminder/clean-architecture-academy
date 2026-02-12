package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.dto.CourseRunResponse;
import com.zuehlke.academy.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.academy.application.ports.TrainerRepository;
import com.zuehlke.academy.academy.application.ports.UserRepository;
import com.zuehlke.academy.academy.domain.CourseRun;
import com.zuehlke.academy.academy.domain.TrainerProfile;
import com.zuehlke.academy.academy.domain.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Loads all details for a given course run, including enrollments and lessons.
 */
@Service
public class LoadCourseRun {

    private final CourseRunRepository courseRunRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    public LoadCourseRun(CourseRunRepository courseRunRepository, TrainerRepository trainerRepository, UserRepository userRepository) {
        this.courseRunRepository = courseRunRepository;
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
    }

    public CourseRunResponse execute(UUID courseRunId) {
        // TODO DISCUSS: introduce read model instead of manual joining?
        CourseRun courseRun = courseRunRepository.findById(courseRunId);
        TrainerProfile trainer = trainerRepository.findById(courseRun.trainerId());
        User user = userRepository.findById(trainer.userId());
        return new CourseRunResponse(courseRun.id().toString(), user.name(), courseRun.availableSeats());
    }
}

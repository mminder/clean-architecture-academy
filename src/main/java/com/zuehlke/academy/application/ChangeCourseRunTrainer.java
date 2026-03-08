package com.zuehlke.academy.application;

import com.zuehlke.academy.application.ports.aggregate.CourseRunRepository;
import com.zuehlke.academy.domain.courseRun.CourseRunCommands;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChangeCourseRunTrainer {

    private final CourseRunRepository courseRunRepository;

    public ChangeCourseRunTrainer(CourseRunRepository courseRunRepository) {
        this.courseRunRepository = courseRunRepository;
    }

    public void execute(UUID courseRunId, UUID trainerProfileId) {
        this.courseRunRepository.changeTrainer(new CourseRunCommands.ChangeTrainer(courseRunId, trainerProfileId));
    }
}

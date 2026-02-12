package com.zuehlke.academy.application;

import com.zuehlke.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.domain.CourseRun;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChangeCourseRunTrainer {

    private final CourseRunRepository courseRunRepository;

    public ChangeCourseRunTrainer(CourseRunRepository courseRunRepository) {
        this.courseRunRepository = courseRunRepository;
    }

    public void execute(UUID courseRunId, UUID trainerProfileId) {
        // TODO DISCUSS: using a dedicated updateTrainer on repository would avoid pre-fetching. But this would make domain superfluous.
        CourseRun courseRun = this.courseRunRepository.findById(courseRunId);
        courseRun.changeTrainer(trainerProfileId);
        this.courseRunRepository.update(courseRun);
    }
}

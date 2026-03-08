package com.zuehlke.academy.application.ports.aggregate;

import com.zuehlke.academy.domain.courseRun.CourseRun;
import com.zuehlke.academy.domain.courseRun.CourseRunCommands;

import java.util.UUID;

public interface CourseRunRepository {
    CourseRun findById(UUID id);

    void changeTrainer(CourseRunCommands.ChangeTrainer changeTrainerCommand);
}

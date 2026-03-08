package com.zuehlke.academy.domain.courseRun;

import java.util.UUID;

public class CourseRunCommands {
    public record ChangeTrainer(UUID courseRunId, UUID trainerId) {}
}

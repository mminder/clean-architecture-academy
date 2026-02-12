package com.zuehlke.academy.academy.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// TODO: this "entity" is not necessary, construct read model directly
public record CourseOverviewReadModelEntity(UUID courseId,
                                            String name,
                                            String description,
                                            List<CourseRunOverviewEntity> courseRuns) {

    // TODO DISCUSS: business logic inside entity seems smelly. Where to put this?
    // also it is a duplicate of domain logic
    public LocalDateTime nextCourseRunStartTime() {
        return courseRuns.stream()
                .map(CourseRunOverviewEntity::firstLessonStartTime)
                .filter(Objects::nonNull)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }

    public record CourseRunOverviewEntity(UUID courseRunId, List<LessonOverviewEntity> lessons) {
        public LocalDateTime firstLessonStartTime() {
            return lessons.stream()
                    .map(lessonOverviewEntity -> lessonOverviewEntity.startTIme)
                    .min(LocalDateTime::compareTo)
                    .orElse(null);
        }
    }

    public record LessonOverviewEntity(UUID lessonId, LocalDateTime startTIme) {
    }
}

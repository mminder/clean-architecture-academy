package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.academy.domain.CourseRun;
import com.zuehlke.academy.academy.domain.Enrollment;
import com.zuehlke.academy.academy.domain.Lesson;
import com.zuehlke.academy.academy.persistence.entity.CourseRunEntity;
import com.zuehlke.academy.academy.persistence.entity.EnrollmentEntity;
import com.zuehlke.academy.academy.persistence.entity.LessonEntity;
import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRunDbRepository implements CourseRunRepository {

    private final CourseRunJdbcRepository courseRunJdbcRepository;

    public CourseRunDbRepository(CourseRunJdbcRepository courseRunJdbcRepository){
        this.courseRunJdbcRepository = courseRunJdbcRepository;
    }

    @Override
    public CourseRun findById(UUID id) {
        CourseRunEntity entity = courseRunJdbcRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("CourseRun not found: " + id));

        List<Lesson> lessons = mapLessons(entity.getLessons());
        List<Enrollment> enrollments = mapEnrollments(entity.getEnrollments());

        return new CourseRun(entity.getId(), entity.getMaxParticipants(), lessons, enrollments, entity.getTrainer().getId());
    }

    @Override
    public void update(CourseRun courseRun) {
        // TODO: Persist updates to enrollments and lessons as needed.
    }

    private List<Lesson> mapLessons(Set<LessonEntity> lessonEntities) {
        if (lessonEntities == null || lessonEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return lessonEntities.stream()
                .filter(Objects::nonNull)
                .map(entity -> new Lesson(
                        entity.getDescription(),
                        entity.getStartTime(),
                        entity.getEndTime()
                ))
                .toList();
    }

    private List<Enrollment> mapEnrollments(Set<EnrollmentEntity> enrollmentEntities) {
        if (enrollmentEntities == null || enrollmentEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return enrollmentEntities.stream()
                .filter(Objects::nonNull)
                .map(entity -> new Enrollment(
                        entity.getId(),
                        entity.getUserId(),
                        entity.getCourseRunId(),
                        entity.getStatus(),
                        entity.getCreatedAt()
                ))
                .toList();
    }
}

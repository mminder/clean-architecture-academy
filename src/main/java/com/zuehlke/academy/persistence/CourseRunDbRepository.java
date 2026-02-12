package com.zuehlke.academy.persistence;

import com.zuehlke.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.domain.CourseRun;
import com.zuehlke.academy.domain.Enrollment;
import com.zuehlke.academy.domain.Lesson;
import com.zuehlke.academy.persistence.entity.CourseRunEntity;
import com.zuehlke.academy.persistence.entity.EnrollmentEntity;
import com.zuehlke.academy.persistence.entity.LessonEntity;
import com.zuehlke.academy.shared.exception.ApplicationException;
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
        CourseRunEntity existing = courseRunJdbcRepository.findById(courseRun.id())
                .orElseThrow(() -> new ApplicationException("CourseRun not found: " + courseRun.id()));

        Set<EnrollmentEntity> enrollmentEntities = mapEnrollmentsToEntities(courseRun.enrollments());

        // TODO DISCUSS: should we offer a complete update or only selectively update what is currently modifiable in the domain?
        // alternative would be to check "existsById" and then do a full update
        CourseRunEntity updated = new CourseRunEntity(
                existing.getId(),
                existing.getVersion(),
                existing.getMaxParticipants(),
                existing.getTrainer(),
                existing.getLessons(),
                enrollmentEntities
        );

        courseRunJdbcRepository.save(updated);
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

    private Set<EnrollmentEntity> mapEnrollmentsToEntities(List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.isEmpty()) {
            return new LinkedHashSet<>();
        }
        return enrollments.stream()
                .filter(Objects::nonNull)
                .map(enrollment -> new EnrollmentEntity(
                        enrollment.id(),
                        null,
                        enrollment.userId(),
                        enrollment.courseRunId(),
                        enrollment.status(),
                        enrollment.createdAt()
                ))
                .collect(java.util.stream.Collectors.toCollection(LinkedHashSet::new));
    }
}

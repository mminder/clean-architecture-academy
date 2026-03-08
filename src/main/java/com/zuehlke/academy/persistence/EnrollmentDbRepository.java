package com.zuehlke.academy.persistence;

import com.zuehlke.academy.application.ports.aggregate.EnrollmentRepository;
import com.zuehlke.academy.domain.Enrollment;
import com.zuehlke.academy.persistence.entity.EnrollmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class EnrollmentDbRepository implements EnrollmentRepository {

    private final EnrollmentJdbcRepository enrollmentJdbcRepository;

    public EnrollmentDbRepository(EnrollmentJdbcRepository enrollmentJdbcRepository) {
        this.enrollmentJdbcRepository = enrollmentJdbcRepository;
    }

    @Override
    public List<Enrollment> findAllByUser(UUID userId) {
        List<EnrollmentEntity> enrollments = enrollmentJdbcRepository.findByUserId(userId);
        return enrollments.stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void create(Enrollment enrollment) {
        EnrollmentEntity entity = new EnrollmentEntity(
                enrollment.id(),
                null,
                enrollment.userId(),
                enrollment.courseRunId(),
                enrollment.status(),
                enrollment.createdAt()
        );
        enrollmentJdbcRepository.save(entity);
    }

    @Override
    public List<Enrollment> findAllByCourseRun(UUID courseRunId) {
        List<EnrollmentEntity> enrollments = enrollmentJdbcRepository.findByCourseRunId(courseRunId);
        return enrollments.stream()
                .map(this::toDomain)
                .toList();
    }

    private Enrollment toDomain(EnrollmentEntity entity) {
        return new Enrollment(
                entity.getId(),
                entity.getUserId(),
                entity.getCourseRunId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}

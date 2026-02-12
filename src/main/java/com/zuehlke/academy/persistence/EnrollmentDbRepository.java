package com.zuehlke.academy.persistence;

import com.zuehlke.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.domain.Enrollment;
import com.zuehlke.academy.persistence.entity.EnrollmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

// TODO DISCUSS: how do I prevent write operations on this? Updates must always go through CourseRun Aggregate Root
@Repository
public class EnrollmentDbRepository implements EnrollmentRepository {

    private final EnrollmentJdbcRepository enrollmentJdbcRepository;

    public EnrollmentDbRepository(EnrollmentJdbcRepository enrollmentJdbcRepository) {
        this.enrollmentJdbcRepository = enrollmentJdbcRepository;
    }

    @Override
    public List<Enrollment> findAllEnrollmentsForUser(UUID userId) {
        List<EnrollmentEntity> enrollments = enrollmentJdbcRepository.findByUserId(userId);
        return enrollments.stream()
                .map(this::toDomain)
                .toList();
    }

    private Enrollment toDomain(EnrollmentEntity entity) {
        return new Enrollment(
                entity.getId(),
                entity.getUserId(),
                entity.getCourseRunId(), // courseRunId - not in current schema
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}

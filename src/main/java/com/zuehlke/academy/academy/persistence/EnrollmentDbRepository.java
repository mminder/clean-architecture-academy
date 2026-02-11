package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.academy.application.readmodel.UserCourseRunEnrollmentReadModel;
import com.zuehlke.academy.academy.persistence.entity.EnrollmentEntity;
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
    public List<UserCourseRunEnrollmentReadModel> findAllCourseRunEnrollmentsForUser(UUID userId) {
        List<EnrollmentEntity> enrollments = enrollmentJdbcRepository.findByUserId(userId);
        return enrollments.stream()
                .map(this::toReadModel)
                .toList();
    }

    private UserCourseRunEnrollmentReadModel toReadModel(EnrollmentEntity entity) {
        return new UserCourseRunEnrollmentReadModel(
                entity.getId(),
                entity.getCourseRunId(), // courseRunId - not in current schema
                entity.getUserId(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}

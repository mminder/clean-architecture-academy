package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.EnrollmentRepository;
import com.zuehlke.academy.academy.application.readmodel.UserCourseRunEnrollmentReadModel;
import com.zuehlke.academy.academy.domain.Enrollment;
import com.zuehlke.academy.academy.domain.EnrollmentStatus;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public class EnrollmentDbRepository implements EnrollmentRepository {

    @Override
    public List<UserCourseRunEnrollmentReadModel> findAllCourseRunEnrollmentsForUser(UUID userId) {
        return List.of(
                new UserCourseRunEnrollmentReadModel(
                        UUID.randomUUID(),
                        UUID.randomUUID(),
                        userId,
                        EnrollmentStatus.CONFIRMED,
                        Instant.now()
                )
        );
    }
}

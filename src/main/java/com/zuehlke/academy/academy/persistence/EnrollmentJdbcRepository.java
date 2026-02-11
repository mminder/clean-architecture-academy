package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.persistence.entity.EnrollmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnrollmentJdbcRepository extends CrudRepository<EnrollmentEntity, UUID> {
    List<EnrollmentEntity> findByUserId(UUID userId);
}

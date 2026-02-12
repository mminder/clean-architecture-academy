package com.zuehlke.academy.persistence;

import com.zuehlke.academy.persistence.entity.CourseRunEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRunJdbcRepository extends CrudRepository<CourseRunEntity, UUID> {
}

package com.zuehlke.academy.persistence;

import com.zuehlke.academy.persistence.entity.TrainerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainerJdbcRepository extends CrudRepository<TrainerEntity, UUID> {
}

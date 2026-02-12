package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.persistence.entity.TrainerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainerJdbcRepository extends CrudRepository<TrainerEntity, UUID> {
}

package com.zuehlke.academy.persistence;

import com.zuehlke.academy.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJdbcRepository extends CrudRepository<UserEntity, UUID> {
}

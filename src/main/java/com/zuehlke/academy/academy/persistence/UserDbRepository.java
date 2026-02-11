package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.UserRepository;
import com.zuehlke.academy.academy.domain.User;
import com.zuehlke.academy.academy.domain.shared.Email;
import com.zuehlke.academy.academy.persistence.entity.UserEntity;
import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class UserDbRepository implements UserRepository {

    private final UserJdbcRepository userJdbcRepository;

    public UserDbRepository(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    @Override
    public User findById(UUID userId) {
        UserEntity userEntity = userJdbcRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("User not found: " + userId));

        return new User(
                userEntity.getId(),
                new ArrayList<>(userEntity.getRoles()),
                userEntity.getName(),
                new Email(userEntity.getEmail())
        );
    }
}

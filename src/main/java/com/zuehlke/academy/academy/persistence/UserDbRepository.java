package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.UserRepository;
import com.zuehlke.academy.academy.domain.Role;
import com.zuehlke.academy.academy.domain.User;
import com.zuehlke.academy.academy.domain.shared.Email;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDbRepository implements UserRepository {

    @Override
    public User findById(UUID userId) {
        Email email = new Email("user+" + userId + "@example.com");
        return new User(userId, List.of(Role.STUDENT), "Sample User", email);
    }
}

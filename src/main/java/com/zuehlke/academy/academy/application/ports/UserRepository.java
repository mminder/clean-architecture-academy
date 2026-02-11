package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.domain.User;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);
}

package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.domain.User;

import java.util.UUID;

public interface UserRepository {
    User findById(UUID userId);
}

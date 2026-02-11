package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.domain.shared.Email;

import java.util.List;
import java.util.UUID;

public class User {
    private final UUID id;
    private final List<Role> roles;
    private final String name;
    private final Email email;

    public User(UUID id, List<Role> roles, String name, Email email) {
        this.id = id;
        this.roles = roles;
        this.name = name;
        this.email = email;
    }
}

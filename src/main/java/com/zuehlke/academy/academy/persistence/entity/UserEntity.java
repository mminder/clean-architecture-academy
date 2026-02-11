package com.zuehlke.academy.academy.persistence.entity;

import com.zuehlke.academy.academy.domain.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Table("users")
public class UserEntity {

    @Id
    private UUID id;

    private String name;

    private String email;

    @MappedCollection(idColumn = "user_id", keyColumn = "role")
    private Set<Role> roles;

    public UserEntity(UUID id, String name, String email, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}

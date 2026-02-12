package com.zuehlke.academy.persistence.entity;

import com.zuehlke.academy.domain.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Table("users")
public class UserEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private String name;

    private String email;

    @MappedCollection(idColumn = "user_id", keyColumn = "role")
    private Set<Role> roles;

    public UserEntity(UUID id, Long version, String name, String email, Set<Role> roles) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
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

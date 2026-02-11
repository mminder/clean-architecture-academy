package com.zuehlke.academy.academy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("trainers")
public class TrainerEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private UUID userId;

    private String profileDescription;

    public TrainerEntity(UUID id, Long version, UUID userId, String profileDescription) {
        this.id = id;
        this.version = version;
        this.userId = userId;
        this.profileDescription = profileDescription;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getProfileDescription() {
        return profileDescription;
    }
}


package com.zuehlke.academy.academy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("trainers")
public class TrainerEntity {

    @Id
    private UUID id;

    private UUID userId;

    private String profileDescription;

    public TrainerEntity(UUID id, UUID userId, String profileDescription) {
        this.id = id;
        this.userId = userId;
        this.profileDescription = profileDescription;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getProfileDescription() {
        return profileDescription;
    }
}

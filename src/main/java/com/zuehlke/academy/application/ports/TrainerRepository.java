package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.domain.TrainerProfile;

import java.util.UUID;

public interface TrainerRepository {
    TrainerProfile findById(UUID trainerId);
}

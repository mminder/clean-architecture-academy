package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.domain.TrainerProfile;
import com.zuehlke.academy.academy.domain.User;

import java.util.UUID;

public interface TrainerRepository {
    TrainerProfile findById(UUID trainerId);
}

package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.TrainerRepository;
import com.zuehlke.academy.academy.domain.TrainerProfile;
import com.zuehlke.academy.academy.persistence.entity.TrainerEntity;
import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class TrainerDbRepository implements TrainerRepository {

    private final TrainerJdbcRepository trainerJdbcRepository;

    public TrainerDbRepository(TrainerJdbcRepository trainerJdbcRepository) {
        this.trainerJdbcRepository = trainerJdbcRepository;
    }

    @Override
    public TrainerProfile findById(UUID trainerId) {
        TrainerEntity trainerEntity = trainerJdbcRepository.findById(trainerId)
                .orElseThrow(() -> new ApplicationException("TrainerProfile not found: " + trainerId));

        return new TrainerProfile(trainerEntity.getId(), trainerEntity.getProfileDescription(), trainerEntity.getUserId());
    }
}

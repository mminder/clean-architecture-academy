package com.zuehlke.academy.persistence;

import com.zuehlke.academy.application.ports.aggregate.CourseRunRepository;
import com.zuehlke.academy.domain.courseRun.CourseRun;
import com.zuehlke.academy.domain.courseRun.CourseRunCommands;
import com.zuehlke.academy.persistence.entity.CourseRunEntity;
import com.zuehlke.academy.shared.exception.ApplicationException;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CourseRunDbRepository implements CourseRunRepository {

    private final CourseRunJdbcRepository courseRunJdbcRepository;

    public CourseRunDbRepository(CourseRunJdbcRepository courseRunJdbcRepository) {
        this.courseRunJdbcRepository = courseRunJdbcRepository;
    }

    @Override
    public CourseRun findById(UUID id) {
        CourseRunEntity entity = courseRunJdbcRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("CourseRun not found: " + id));

        return new CourseRun(entity.getId(), entity.getMaxParticipants());
    }

    @Override
    public void changeTrainer(CourseRunCommands.ChangeTrainer changeTrainerCommand) {
        CourseRunEntity entity = courseRunJdbcRepository.findById(changeTrainerCommand.courseRunId())
                .orElseThrow(() -> new ApplicationException("CourseRun not found: " + changeTrainerCommand.courseRunId()));

        entity.setTrainer(AggregateReference.to(changeTrainerCommand.trainerId()));

        courseRunJdbcRepository.save(entity);
    }

}

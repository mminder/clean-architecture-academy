package com.zuehlke.academy.persistence;

import com.zuehlke.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.domain.CourseRun;
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

        return new CourseRun(entity.getId(), entity.getVersion(), entity.getMaxParticipants(), entity.getTrainer().getId());
    }

    @Override
    public void update(CourseRun courseRun) {
        // TODO: what is the performance impact of existsById? We already fetched the entity in use case
        if (!courseRunJdbcRepository.existsById(courseRun.id())) {
            throw new ApplicationException("CourseRun not found: " + courseRun.id());
        }

        CourseRunEntity updated = new CourseRunEntity(
                courseRun.id(),
                courseRun.version(),
                courseRun.maxParticipants(),
                AggregateReference.to(courseRun.trainerId())
        );

        courseRunJdbcRepository.save(updated);
    }

}

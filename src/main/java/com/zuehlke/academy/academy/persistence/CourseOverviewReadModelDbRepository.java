package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.CourseOverviewReadModelRepository;
import com.zuehlke.academy.academy.application.dto.CourseOverviewResponse;
import com.zuehlke.academy.academy.persistence.entity.CourseOverviewReadModelEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseOverviewReadModelDbRepository implements CourseOverviewReadModelRepository {

    private final CourseOverviewReadModelJdbcRepository courseOverviewReadModelJdbcRepository;

    public CourseOverviewReadModelDbRepository(CourseOverviewReadModelJdbcRepository courseOverviewReadModelJdbcRepository) {
        this.courseOverviewReadModelJdbcRepository = courseOverviewReadModelJdbcRepository;
    }

    @Override
    public List<CourseOverviewResponse> findAllCourseOverviews() {
        return courseOverviewReadModelJdbcRepository.findAll().stream()
                .map(this::toReadModel)
                .toList();
    }

    private CourseOverviewResponse toReadModel(CourseOverviewReadModelEntity entity) {
        return new CourseOverviewResponse(
                entity.courseId(),
                entity.name(),
                entity.description(),
                entity.nextCourseRunStartTime()
        );
    }
}

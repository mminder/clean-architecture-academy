package com.zuehlke.academy.application.ports.query;

import com.zuehlke.academy.application.dto.CourseOverviewResponse;

import java.util.List;

public interface CourseOverviewQueryRepository {
    List<CourseOverviewResponse> findAllCourseOverviews();
}

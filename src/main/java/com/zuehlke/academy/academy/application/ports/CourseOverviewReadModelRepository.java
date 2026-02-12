package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.application.dto.CourseOverviewResponse;

import java.util.List;

public interface CourseOverviewReadModelRepository {
    List<CourseOverviewResponse> findAllCourseOverviews();
}

package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.application.dto.CourseOverviewResponse;

import java.util.List;

public interface CourseOverviewReadModelRepository {
    List<CourseOverviewResponse> findAllCourseOverviews();
}

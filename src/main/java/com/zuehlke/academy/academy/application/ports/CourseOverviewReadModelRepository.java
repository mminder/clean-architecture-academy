package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.application.readmodel.CourseOverviewReadModel;

import java.util.List;

public interface CourseOverviewReadModelRepository {
    List<CourseOverviewReadModel> findAllCourseOverviews();
}

package com.zuehlke.academy.persistence;

import com.zuehlke.academy.application.dto.CourseOverviewResponse;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class CourseOverviewReadModelJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CourseOverviewReadModelJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CourseOverviewResponse> findAll() {
        String sql = """
                SELECT c.id AS course_id,
                       c.name AS course_name,
                       c.description AS course_description,
                       MIN(l.start_time) AS next_course_run_start_time
                FROM courses c
                LEFT JOIN course_course_runs ccr ON ccr.course_id = c.id
                LEFT JOIN course_runs cr ON cr.id = ccr.course_run_id
                LEFT JOIN lessons l ON l.course_run_id = cr.id
                GROUP BY c.id, c.name, c.description
                ORDER BY c.name
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new CourseOverviewResponse(
                rs.getObject("course_id", UUID.class),
                rs.getString("course_name"),
                rs.getString("course_description"),
                rs.getObject("next_course_run_start_time", LocalDateTime.class)
        ));
    }
}

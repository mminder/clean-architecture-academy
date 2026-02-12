package com.zuehlke.academy.persistence.query;

import com.zuehlke.academy.application.dto.CourseRunDetailsResponse;
import com.zuehlke.academy.application.ports.query.CourseRunDetailsQueryRepository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CourseRunDetailsQueryDbRepository implements CourseRunDetailsQueryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CourseRunDetailsQueryDbRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CourseRunDetailsResponse findCourseRunDetailsByCourseRunId(UUID courseRunId) {
        String sql = """
                SELECT c.id AS course_id,
                       c.name AS course_name,
                       cr.id AS course_run_id,
                       u.name AS trainer_name,
                       cr.max_participants - COUNT(e.id) AS available_seats
                FROM course_runs cr
                JOIN trainers t ON t.id = cr.trainer
                JOIN users u ON u.id = t.user_id
                JOIN course_course_runs ccr ON ccr.course_run_id = cr.id
                JOIN courses c ON c.id = ccr.course_id
                LEFT JOIN enrollments e ON e.course_run_id = cr.id AND e.status = 'ENROLLED'
                WHERE cr.id = :courseRunId
                GROUP BY c.id, c.name, cr.id, u.name, cr.max_participants
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("courseRunId", courseRunId);

        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> new CourseRunDetailsResponse(
                rs.getObject("course_id", UUID.class).toString(),
                rs.getString("course_name"),
                rs.getObject("course_run_id", UUID.class).toString(),
                rs.getString("trainer_name"),
                rs.getInt("available_seats")
        ));
    }
}

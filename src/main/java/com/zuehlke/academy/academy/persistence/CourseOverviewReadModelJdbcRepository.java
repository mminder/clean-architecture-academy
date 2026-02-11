package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.persistence.entity.CourseOverviewReadModelEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class CourseOverviewReadModelJdbcRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CourseOverviewReadModelJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CourseOverviewReadModelEntity> findAll() {
        String sql = """
                SELECT c.id AS course_id,
                       c.name AS course_name,
                       c.description AS course_description,
                       cr.id AS course_run_id,
                       l.id AS lesson_id,
                       l.start_time AS lesson_start_time
                FROM courses c
                LEFT JOIN course_course_runs ccr ON ccr.course_id = c.id
                LEFT JOIN course_runs cr ON cr.id = ccr.course_run_id
                LEFT JOIN lessons l ON l.course_run_id = cr.id
                ORDER BY c.name, cr.id, l.start_time
                """;

        List<Row> rows = jdbcTemplate.query(sql, this::mapRow);

        Map<UUID, CourseOverviewReadModelEntity> byCourse = new LinkedHashMap<>();
        Map<UUID, Map<UUID, CourseOverviewReadModelEntity.CourseRunOverviewEntity>> byCourseRun = new LinkedHashMap<>();

        // TODO DISCUSS: is there a better, easier way to map this?
        for (Row row : rows) {
            CourseOverviewReadModelEntity course = byCourse.get(row.courseId);
            if (course == null) {
                course = new CourseOverviewReadModelEntity(
                        row.courseId,
                        row.courseName,
                        row.courseDescription,
                        new ArrayList<>()
                );
                byCourse.put(row.courseId, course);
                byCourseRun.put(row.courseId, new LinkedHashMap<>());
            }

            if (row.courseRunId != null) {
                Map<UUID, CourseOverviewReadModelEntity.CourseRunOverviewEntity> courseRuns = byCourseRun.get(row.courseId);
                CourseOverviewReadModelEntity.CourseRunOverviewEntity courseRun = courseRuns.get(row.courseRunId);
                if (courseRun == null) {
                    courseRun = new CourseOverviewReadModelEntity.CourseRunOverviewEntity(
                            row.courseRunId,
                            new ArrayList<>()
                    );
                    courseRuns.put(row.courseRunId, courseRun);
                    course.courseRuns().add(courseRun);
                }

                if (row.lessonId != null) {
                    courseRun.lessons().add(
                            new CourseOverviewReadModelEntity.LessonOverviewEntity(
                                    row.lessonId,
                                    row.lessonStartTime
                            )
                    );
                }
            }
        }

        return new ArrayList<>(byCourse.values());
    }

    private Row mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Row(
                rs.getObject("course_id", UUID.class),
                rs.getString("course_name"),
                rs.getString("course_description"),
                rs.getObject("course_run_id", UUID.class),
                rs.getObject("lesson_id", UUID.class),
                rs.getObject("lesson_start_time", LocalDateTime.class)
        );
    }

    private record Row(UUID courseId,
                       String courseName,
                       String courseDescription,
                       UUID courseRunId,
                       UUID lessonId,
                       LocalDateTime lessonStartTime) {
    }
}

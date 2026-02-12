package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import com.zuehlke.academy.academy.shared.validation.Validation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CourseRun {
    private final UUID id;
    private final int maxParticipants;
    private final List<Lesson> lessons;
    private final List<Enrollment> enrollments;
    private final UUID trainerId;

    public CourseRun(UUID id, int maxParticipants, List<Lesson> lessons, List<Enrollment> enrollments, UUID trainerId) {
        this.id = Validation.notNull(id, "CourseRun id must not be null");
        this.maxParticipants = Validation.minInt(maxParticipants, 1, "CourseRun maxParticipants must be at least 1");
        this.lessons = Validation.notNull(lessons, "CourseRun lessons must not be null");
        this.enrollments = Validation.notNull(enrollments, "CourseRun enrollments must not be null");
        this.trainerId = Validation.notNull(trainerId, "CourseRun enrollments trainer not be null");
    }

    public LocalDateTime startTime() {
        return lessons.stream()
                .map(Lesson::startTime)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }

    public UUID id() {
        return id;
    }

    public UUID trainerId() {
        return trainerId;
    }

    public void enrollUser(UUID userId) {
        List<Enrollment> confirmedEnrollments = enrollments.stream().filter(Enrollment::isConfirmed).toList();
        if (confirmedEnrollments.size() >= maxParticipants) {
            throw new ApplicationException("Enrollment not possible, CourseRun is full");
        }
        if (confirmedEnrollments.stream().anyMatch(enrollment -> enrollment.userId().equals(userId))) {
            throw new ApplicationException("Enrollment not possible, user is already enrolled");
        }
        Enrollment newEnrollment = new Enrollment(UUID.randomUUID(), userId, this.id, EnrollmentStatus.CONFIRMED, Instant.now());
        enrollments.add(newEnrollment);
    }

    public int availableSeats() {
        List<Enrollment> confirmedEnrollments = enrollments.stream().filter(Enrollment::isConfirmed).toList();
        return maxParticipants - confirmedEnrollments.size();
    }
}

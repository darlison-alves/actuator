package com.hp.actuator.adapters.inBound.controller;

import com.hp.actuator.adapters.outBound.persistence.SpringDataRedisStudentRepository;
import com.hp.actuator.adapters.outBound.persistence.entities.Student;
import com.hp.actuator.application.services.RedisStateService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Timed
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private SpringDataRedisStudentRepository springDataRedisStudentRepository;

    @Autowired
    private RedisStateService redisStateService;

    Timer findStudentsTimer;

    public StudentController(MeterRegistry registry) {
        findStudentsTimer = registry.timer("http_requests", "method", "GET");
    }

    @GetMapping
    @Timed
    @Timed(value = "all_students", longTask = true, description = "list of students")
    public ResponseEntity<List<Student>> findAll() {
        return findStudentsTimer.record(() -> { // use the timer!
            List<Student> students = this.springDataRedisStudentRepository.findAll();
            return ResponseEntity.ok(students);
        });
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping
    public ResponseEntity<Student> save() {
        Student student = new Student("DARLINSON ALVES", Student.Gender.MALE, 1);
        this.springDataRedisStudentRepository.save(student);
        return ResponseEntity.ok(student);
    }
}

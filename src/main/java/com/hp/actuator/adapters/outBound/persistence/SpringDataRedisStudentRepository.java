package com.hp.actuator.adapters.outBound.persistence;

import com.hp.actuator.adapters.outBound.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataRedisStudentRepository extends JpaRepository<Student, String> {}

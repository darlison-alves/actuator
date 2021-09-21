package com.hp.actuator.adapters.outBound.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Student")
@Getter
@Setter
public class Student implements Serializable {

    public enum Gender { MALE, FEMALE };

    private String id;
    private String name;
    private Gender gender;
    private int grade;

    public Student(String name, Gender gender, int grade) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }

}

package com.g2.Progweb_II_EducaDin_Backend.enums;

import lombok.Getter;

@Getter
public enum Repeatable {
    WEEKLY(1L,"weekly"),
    MONTHLY(2L,"monthly"),
    YEARLY(3L,"yearly"),
    DONT_REPEATS(4L,"dont_repeats");

    private Long id;
    private String name;

    Repeatable(Long id, String name) {
        this.id = id;
        this.name = name;
    }


}

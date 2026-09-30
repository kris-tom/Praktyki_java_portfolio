package com.krdl.task10;

import java.time.LocalDate;

public class Animal {
    private String name;
    private String type;
    private LocalDate dateOfBirth;

    public Animal(String name, String type, LocalDate dateOfBirth) {
        this.name = name;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " (" + type + ", ur. " + dateOfBirth + ")";
    }
}
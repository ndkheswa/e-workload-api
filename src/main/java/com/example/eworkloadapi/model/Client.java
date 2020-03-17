package com.example.eworkloadapi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
public class Client {

    private enum Gender {
        M, F
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate birthDate;

    private int age;

    public void setAge(LocalDate birthDate, LocalDate currentDate) {
        this.age = calculateAge(birthDate, currentDate);
    }

    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate != null && currentDate != null) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}

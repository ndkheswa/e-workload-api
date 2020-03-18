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
        MALE, FEMALE, OTHER
    }

    private enum Occupation {
        STUDENT, SCHOLAR, SALARIED_EMPLOYEE, SELF_EMPLOYED, BUSINESS_OWNER, UNEMPLOYED
    }

    private enum MaritalStatus {
        SINGLE, MARRIED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String email;

    private String phone;

    @NotNull
    private Occupation occupation;

    @NotNull
    private Gender gender;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String maritalStatus;

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

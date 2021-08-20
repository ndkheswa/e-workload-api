package com.learncoding.eworkloadapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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
    @Column(name = "firstname")
    private String firstName;

    @NotNull
    @Column(name = "lastname")
    private String lastName;

    private String email;

    private String phone;

    @NotNull
    private String occupation;

    @NotNull
    private String gender;

    @NotNull
    @Column(name = "birthdate")
    private Date birthDate;

    @NotNull
    @Column(name = "maritalstatus")
    private String maritalStatus;

    private int age;

    public void setAge(Date birthDate) {
        this.age = calculateAge(birthDate);
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private int calculateAge(Date birthDate) {
        return birthDate != null ? Period.between(this.convertToLocalDate(birthDate), LocalDate.now()).getYears() : 0;
    }
}

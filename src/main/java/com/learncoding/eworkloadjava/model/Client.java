package com.learncoding.eworkloadjava.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Client extends AuditModel implements Serializable {

    private static final long serialVersionUID = 6044598961665546255L;

    public Client() { }

    @Id
    @SequenceGenerator(name = "seq-gen", sequenceName = "api_seq", initialValue = 20583748, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="seq-gen")
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    public String lastName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String occupation;

    @NotNull
    private String gender;

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    private int age;

    @OneToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                mappedBy = "client")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    public void setAge(Date birthDate) { this.age = calculateAge(birthDate); }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private int calculateAge(Date birthDate) {
        return birthDate != null ? Period.between(this.convertToLocalDate(birthDate), LocalDate.now()).getYears() : 0;
    }
}
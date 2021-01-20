package com.gdpr.model.persistance;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    @NotNull
    private LocalDate dateOfBirth;

    @Id
    @Column(length = 11)
    @NotNull
    private String pesel;

    @Column
    @NotNull
    private boolean agreementForPersonalData;

    @Column
    @NotNull
    private boolean agreementForPhoneContact;

    @Column
    @NotNull
    private boolean agreementForEmail;

}
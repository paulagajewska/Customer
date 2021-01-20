package com.gdpr.controller;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CustomerDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;
    @NotNull
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{11}")
    private String pesel;
    @NotNull
    private boolean agreementForPersonalData;
    @NotNull
    private boolean agreementForPhoneContact;
    @NotNull
    private boolean agreementForEmail;
}

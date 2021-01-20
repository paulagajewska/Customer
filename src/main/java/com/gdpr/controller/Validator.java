package com.gdpr.controller;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Validator {

    public void validatePeselWithDateOfBirth(CustomerDTO customerDTO) {
        LocalDate date = pareToDate(customerDTO.getPesel());
        if (!date.equals(customerDTO.getDateOfBirth())) {
            throw new DateOfBirthNotCompatibleException("Date of birth is not compatible with pesel");
        }
    }

    private LocalDate pareToDate(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2), 10);
        int month = Integer.parseInt(pesel.substring(2, 4), 10);
        int day = Integer.parseInt(pesel.substring(4, 6), 10);

        if (month > 80) {
            year = year + 1800;
            month = month - 80;
        } else if (month > 60) {
            year = year + 2200;
            month = month - 60;
        } else if (month > 40) {
            year = year + 2100;
            month = month - 40;
        } else if (month > 20) {
            year = year + 2000;
            month = month - 20;
        } else {
            year += 1900;
        }

        return LocalDate.of(year, month, day);
    }

}

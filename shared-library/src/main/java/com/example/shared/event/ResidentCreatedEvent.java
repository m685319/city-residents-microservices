package com.example.shared.event;

import com.example.shared.enums.Gender;

public class ResidentCreatedEvent {

    private Long residentId;
    private String passportNumber;
    private Gender gender;

    public ResidentCreatedEvent() {}

    public ResidentCreatedEvent(Long residentId, String passportNumber, Gender gender) {
        this.residentId = residentId;
        this.passportNumber = passportNumber;
        this.gender = gender;
    }

    public Long getResidentId() {
        return residentId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public Gender getGender() {
        return gender;
    }
}
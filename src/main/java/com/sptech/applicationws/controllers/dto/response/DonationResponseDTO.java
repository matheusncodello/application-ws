package com.sptech.applicationws.controllers.dto.response;

import java.sql.Timestamp;

public class DonationResponseDTO {
    private Long donationId;
    private UserResponseDTO userActive;
    private String donationName;
    private String donationDescription;
    private String donationType;
    private String donationCreated;
    private String donationImage;
    private Boolean isActive;

    public DonationResponseDTO(Long donationId, UserResponseDTO userActive, String donationName, String donationDescription, String donationType, String donationCreated, String donationImage, Boolean isActive) {
        this.donationId = donationId;
        this.userActive = userActive;
        this.donationName = donationName;
        this.donationDescription = donationDescription;
        this.donationType = donationType;
        this.donationCreated = donationCreated;
        this.donationImage = donationImage;
        this.isActive = isActive;
    }

    public DonationResponseDTO() {
    }

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }

    public UserResponseDTO getUserActive() {
        return userActive;
    }

    public void setUserActive(UserResponseDTO userActive) {
        this.userActive = userActive;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public String getDonationDescription() {
        return donationDescription;
    }

    public void setDonationDescription(String donationDescription) {
        this.donationDescription = donationDescription;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getDonationCreated() {
        return donationCreated;
    }

    public void setDonationCreated(String donationCreated) {
        this.donationCreated = donationCreated;
    }

    public String getDonationImage() {
        return donationImage;
    }

    public void setDonationImage(String donationImage) {
        this.donationImage = donationImage;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

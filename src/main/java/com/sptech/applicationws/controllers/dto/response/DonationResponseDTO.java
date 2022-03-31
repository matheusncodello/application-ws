package com.sptech.applicationws.controllers.dto.response;

public class DonationResponseDTO {
    private Long donationId;
    private UserResponseDTO userActive;
    private String donationName;
    private String donationDescription;
    private String donationType;
    private String donationImage;

    public DonationResponseDTO(Long donationId, UserResponseDTO userActive, String donationName, String donationDescription, String donationType, String donationImage) {
        this.donationId = donationId;
        this.userActive = userActive;
        this.donationName = donationName;
        this.donationDescription = donationDescription;
        this.donationType = donationType;
        this.donationImage = donationImage;
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

    public String getDonationImage() {
        return donationImage;
    }

    public void setDonationImage(String donationImage) {
        this.donationImage = donationImage;
    }
}

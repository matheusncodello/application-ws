package com.matheusncodello.applicationws.controllers.dto.request;

import com.matheusncodello.applicationws.domain.enums.Types;

public class DonationRequestDTO {
    private Long fkUser;
    private String donationName;
    private String donationDescription;
    private Types donationType;
    private String donationImage;

    public DonationRequestDTO(Long fkUser, String donationName, String donationDescription, Types donationType, String donationImage) {
        this.fkUser = fkUser;
        this.donationName = donationName;
        this.donationDescription = donationDescription;
        this.donationType = donationType;
        this.donationImage = donationImage;
    }

    public DonationRequestDTO() {
    }

    public Long getFkUser() {
        return fkUser;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
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

    public Types getDonationType() {
        return donationType;
    }

    public void setDonationType(Types donationType) {
        this.donationType = donationType;
    }

    public String getDonationImage() {
        return donationImage;
    }

    public void setDonationImage(String donationImage) {
        this.donationImage = donationImage;
    }
}

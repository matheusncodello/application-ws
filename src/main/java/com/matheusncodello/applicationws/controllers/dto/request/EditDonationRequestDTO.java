package com.matheusncodello.applicationws.controllers.dto.request;

import com.matheusncodello.applicationws.domain.enums.Types;

public class EditDonationRequestDTO {
    private String donationName;
    private String donationDescription;
    private Types donationType;
    private String donationImage;

    public EditDonationRequestDTO(String donationName, String donationDescription, Types donationType, String donationImage) {
        this.donationName = donationName;
        this.donationDescription = donationDescription;
        this.donationType = donationType;
        this.donationImage = donationImage;
    }

    public EditDonationRequestDTO() {
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

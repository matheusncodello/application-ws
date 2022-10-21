package com.matheusncodello.applicationws.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long donationId;

    @NotNull
    @Column(name = "fk_user")
    private Long fkUser;

    @NotBlank
    @Size(max = 50)
    @Column(name = "donation_name")
    private String donationName;

    @NotBlank
    @Size(min = 10, max = 255)
    @Column(name = "donation_description")
    private String donationDescription;

    @PastOrPresent
    @Column(name = "donation_created")
    private Timestamp donationTime;

    @NotBlank
    @Column(name = "donation_type")
    private String donationType;

    @NotBlank
    @Column(name = "donation_image")
    private String donationImage;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public Donation(Long fkUser, String donationName, String donationDescription, Timestamp donationTime, String donationType, String donationImage) {
        this.fkUser = fkUser;
        this.donationName = donationName;
        this.donationDescription = donationDescription;
        this.donationTime = donationTime;
        this.donationType = donationType;
        this.donationImage = donationImage;
    }

    public Donation(){

    }

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
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

    public Timestamp getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(Timestamp donationTime) {
        this.donationTime = donationTime;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

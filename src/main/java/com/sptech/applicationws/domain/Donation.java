package com.sptech.applicationws.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "tb_donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long donationId;
    @Column(name = "fk_user")
    private Long fkUser;
    @Column(name = "donation_name")
    private String donationName;
    @Column(name = "donation_description")
    private String donationDescription;
    @Column(name = "donation_time")
    private Timestamp donationTime;
    @Column(name = "donation_type")
    private String donationType;
    @Column(name = "donation_image")
    private String donationImage;

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
}

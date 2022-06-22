package com.sptech.applicationws.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_post_access")
public class PostAccess {

    @Id
    @NotNull
    @Column(name = "fk_user", nullable = false)
    private Long fkUser;

    @Column(name = "fk_donation")
    private Long fkDonation = null;

    @Column(name = "fk_campaign")
    private Long fkCampaign = null;

    @PastOrPresent
    @Column(name = "access_time")
    private Timestamp accessTime;

    public PostAccess(Long fkUser, Long fkDonation, Long fkCampaign, Timestamp accessTime) {
        this.fkUser = fkUser;
        this.fkDonation = fkDonation;
        this.fkCampaign = fkCampaign;
        this.accessTime = accessTime;
    }

    public PostAccess() {
    }

    public Long getFkUser() {
        return fkUser;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
    }

    public Long getFkDonation() {
        return fkDonation;
    }

    public void setFkDonation(Long fkDonation) {
        this.fkDonation = fkDonation;
    }

    public Long getFkCampaign() {
        return fkCampaign;
    }

    public void setFkCampaign(Long fkCampaign) {
        this.fkCampaign = fkCampaign;
    }

    public Timestamp getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Timestamp accessTime) {
        this.accessTime = accessTime;
    }
}

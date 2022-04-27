package com.sptech.applicationws.domain.helpers;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PostAccessId implements Serializable {
    @Column(name = "fk_user", nullable = false)
    private Long fkUser;
    @Column(name = "fk_donation", nullable = true)
    private Long fkDonation = null;
    @Column(name = "fk_campaign", nullable = true)
    private Long fkCampaign = null;

    public PostAccessId(Long fkUser, Long fkDonation, Long fkCampaign) {
        this.fkUser = fkUser;
        this.fkDonation = fkDonation;
        this.fkCampaign = fkCampaign;
    }

    public PostAccessId() {
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

    public Long getFkUser() {
        return fkUser;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
    }
}

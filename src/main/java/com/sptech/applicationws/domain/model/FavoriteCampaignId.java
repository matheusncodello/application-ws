package com.sptech.applicationws.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FavoriteCampaignId implements Serializable {
    @Column(name = "fk_user")
    private Long fkUser;
    @Column(name = "fk_campaign")
    private Long fkCampaign;

    public FavoriteCampaignId(Long fkUser, Long fkCampaign) {
        this.fkUser = fkUser;
        this.fkCampaign = fkCampaign;
    }

    public FavoriteCampaignId() {
    }

    public Long getFkUser() {
        return fkUser;
    }

    public void setFkUser(Long fkUser) {
        this.fkUser = fkUser;
    }

    public Long getFkCampaign() {
        return fkCampaign;
    }

    public void setFkCampaign(Long fkCampaign) {
        this.fkCampaign = fkCampaign;
    }
}


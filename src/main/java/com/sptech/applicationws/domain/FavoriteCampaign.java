package com.sptech.applicationws.domain;

import com.sptech.applicationws.domain.model.FavoriteCampaignId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_favorite_campaign")
public class FavoriteCampaign implements Serializable {

    @EmbeddedId
    private FavoriteCampaignId id;

    public FavoriteCampaign(FavoriteCampaignId id) {
        this.id = id;
    }

    public FavoriteCampaign() {
    }

    public FavoriteCampaignId getId() {
        return id;
    }

    public void setId(FavoriteCampaignId id) {
        this.id = id;
    }
}

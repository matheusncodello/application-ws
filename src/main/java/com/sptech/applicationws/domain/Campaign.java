package com.sptech.applicationws.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_campaign")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_id")
    private Long campaignId;

    @NotNull
    @Column(name = "fk_ong")
    private Long fkOng;

    @NotBlank
    @Size(max = 50)
    @Column(name = "campaign_name")
    private String campaignName;

    @NotBlank
    @Size(min = 10, max = 255)
    @Column(name = "campaign_description")
    private String campaignDescription;

    @PastOrPresent
    @Column(name = "campaign_created")
    private Timestamp campaignTime;

    @NotBlank
    @Column(name = "campaign_type")
    private String campaignType;

    @NotBlank
    @Column(name = "campaign_image")
    private String campaignImage;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public Campaign(Long fkOng, String campaignName, String campaignDescription, Timestamp campaignTime, String campaignType, String campaignImage) {
        this.fkOng = fkOng;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignTime = campaignTime;
        this.campaignType = campaignType;
        this.campaignImage = campaignImage;
    }

    public Campaign() {
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getFkOng() {
        return fkOng;
    }

    public void setFkOng(Long fkOng) {
        this.fkOng = fkOng;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    public Timestamp getCampaignTime() {
        return campaignTime;
    }

    public void setCampaignTime(Timestamp campaignTime) {
        this.campaignTime = campaignTime;
    }

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public String getCampaignImage() {
        return campaignImage;
    }

    public void setCampaignImage(String campaignImage) {
        this.campaignImage = campaignImage;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

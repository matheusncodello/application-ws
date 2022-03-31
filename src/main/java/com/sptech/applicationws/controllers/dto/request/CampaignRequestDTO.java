package com.sptech.applicationws.controllers.dto.request;

import com.sptech.applicationws.domain.enums.Types;

public class CampaignRequestDTO {
    private Long fkOng;
    private String campaignName;
    private String campaignDescription;
    private Types campaignType;
    private String campaignImage;

    public CampaignRequestDTO(Long fkOng, String campaignName, String campaignDescription, Types campaignType, String campaignImage) {
        this.fkOng = fkOng;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignType = campaignType;
        this.campaignImage = campaignImage;
    }

    public CampaignRequestDTO() {
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

    public Types getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(Types campaignType) {
        this.campaignType = campaignType;
    }

    public String getCampaignImage() {
        return campaignImage;
    }

    public void setCampaignImage(String campaignImage) {
        this.campaignImage = campaignImage;
    }
}

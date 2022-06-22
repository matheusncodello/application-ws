package com.matheusncodello.applicationws.controllers.dto.request;

import com.matheusncodello.applicationws.domain.enums.Types;

public class EditCampaignRequestDTO {
    private String campaignName;
    private String campaignDescription;
    private Types campaignType;
    private String campaignImage;

    public EditCampaignRequestDTO(String campaignName, String campaignDescription, Types campaignType, String campaignImage) {
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignType = campaignType;
        this.campaignImage = campaignImage;
    }

    public EditCampaignRequestDTO() {
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

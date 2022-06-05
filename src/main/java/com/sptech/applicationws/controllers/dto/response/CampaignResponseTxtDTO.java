package com.sptech.applicationws.controllers.dto.response;

import lombok.extern.java.Log;

public class CampaignResponseTxtDTO {
    private UserResponseDTO ongActive;
    private String campaignName;
    private String campaignDescription;
    private String campaignCreated;
    private Long AccessCount;

    public CampaignResponseTxtDTO(UserResponseDTO ongActive, String campaignName, String campaignDescription, String campaignCreated, Long AccessCount) {
        this.ongActive = ongActive;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignCreated = campaignCreated;
        this.AccessCount = AccessCount;

    }

    public CampaignResponseTxtDTO() {
    }

    public UserResponseDTO getOngActive() {
        return ongActive;
    }

    public void setOngActive(UserResponseDTO ongActive) {
        this.ongActive = ongActive;
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

    public String getCampaignCreated() {
        return campaignCreated;
    }

    public void setCampaignCreated(String campaignCreated) {
        this.campaignCreated = campaignCreated;
    }

    public Long getAccessCount() {
        return AccessCount;
    }

    public void setAccessCount(Long accessCount) {
        AccessCount = accessCount;
    }


}

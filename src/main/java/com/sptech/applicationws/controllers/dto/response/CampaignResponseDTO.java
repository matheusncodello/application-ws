package com.sptech.applicationws.controllers.dto.response;

public class CampaignResponseDTO {
    private Long campaignId;
    private UserResponseDTO ongActive;
    private String campaignName;
    private String campaignDescription;
    private String campaignType;
    private String campaignImage;

    public CampaignResponseDTO(Long campaignId, UserResponseDTO ongActive, String campaignName, String campaignDescription, String campaignType, String campaignImage) {
        this.campaignId = campaignId;
        this.ongActive = ongActive;
        this.campaignName = campaignName;
        this.campaignDescription = campaignDescription;
        this.campaignType = campaignType;
        this.campaignImage = campaignImage;
    }

    public CampaignResponseDTO() {
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
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
}

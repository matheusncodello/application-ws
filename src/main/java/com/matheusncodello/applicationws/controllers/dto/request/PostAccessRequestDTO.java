package com.matheusncodello.applicationws.controllers.dto.request;

public class PostAccessRequestDTO {
    private Long fkUser;
    private Long fkDonation = null;
    private Long fkCampaign = null;

    public PostAccessRequestDTO(Long fkUser, Long fkDonation, Long fkCampaign) {
        this.fkUser = fkUser;
        this.fkDonation = fkDonation;
        this.fkCampaign = fkCampaign;
    }

    public PostAccessRequestDTO() {
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
}

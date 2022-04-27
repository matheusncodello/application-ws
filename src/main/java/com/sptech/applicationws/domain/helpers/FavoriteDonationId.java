package com.sptech.applicationws.domain.helpers;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FavoriteDonationId implements Serializable {
    @Column(name = "fk_ong")
    private Long fkOng;
    @Column(name = "fk_donation")
    private Long fkDonation;

    public FavoriteDonationId(Long fkOng, Long fkDonation) {
        this.fkOng = fkOng;
        this.fkDonation = fkDonation;
    }

    public FavoriteDonationId() {
    }

    public Long getFkOng() {
        return fkOng;
    }

    public void setFkOng(Long fkOng) {
        this.fkOng = fkOng;
    }

    public Long getFkDonation() {
        return fkDonation;
    }

    public void setFkDonation(Long fkDonation) {
        this.fkDonation = fkDonation;
    }
}

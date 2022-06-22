package com.matheusncodello.applicationws.domain;

import com.matheusncodello.applicationws.domain.helpers.FavoriteDonationId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_favorite_donation")
public class FavoriteDonation implements Serializable {

    @EmbeddedId
    private FavoriteDonationId id;

    public FavoriteDonation(FavoriteDonationId id) {
        this.id = id;
    }

    public FavoriteDonation() {
    }

    public FavoriteDonationId getId() {
        return id;
    }

    public void setId(FavoriteDonationId id) {
        this.id = id;
    }
}

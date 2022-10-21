package com.matheusncodello.applicationws.infra.database;

import com.matheusncodello.applicationws.domain.FavoriteDonation;
import com.matheusncodello.applicationws.domain.helpers.FavoriteDonationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDonationRepository extends JpaRepository<FavoriteDonation, FavoriteDonationId> {
}

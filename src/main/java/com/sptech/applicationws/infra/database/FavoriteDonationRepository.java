package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.FavoriteDonation;
import com.sptech.applicationws.domain.model.FavoriteDonationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteDonationRepository extends JpaRepository<FavoriteDonation, FavoriteDonationId> {
}

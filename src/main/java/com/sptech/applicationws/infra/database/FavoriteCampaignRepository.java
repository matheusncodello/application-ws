package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.FavoriteCampaign;
import com.sptech.applicationws.domain.model.FavoriteCampaignId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCampaignRepository extends JpaRepository<FavoriteCampaign, FavoriteCampaignId> {
}

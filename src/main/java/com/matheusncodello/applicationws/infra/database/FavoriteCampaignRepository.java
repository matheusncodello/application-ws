package com.matheusncodello.applicationws.infra.database;

import com.matheusncodello.applicationws.domain.FavoriteCampaign;
import com.matheusncodello.applicationws.domain.helpers.FavoriteCampaignId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCampaignRepository extends JpaRepository<FavoriteCampaign, FavoriteCampaignId> {
}

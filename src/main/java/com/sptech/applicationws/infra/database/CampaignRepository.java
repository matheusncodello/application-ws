package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByFkOng(Long fkOng);

    @Query(value =
            "SELECT * FROM tb_campaign AS tbc \n" +
            "JOIN tb_favorite_campaign AS tfc ON tbc.campaign_id = tfc.fk_campaign \n" +
            "WHERE tfc.fk_user = :userId", nativeQuery = true)
    List<Campaign> getFavoriteCampaign(@Param("userId") Long userId);
}

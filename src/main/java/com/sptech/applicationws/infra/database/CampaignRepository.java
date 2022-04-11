package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByFkOng(Long fkOng);
}

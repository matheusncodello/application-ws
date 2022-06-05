package com.sptech.applicationws.infra.database;

import com.sptech.applicationws.domain.PostAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface PostAccessRepository extends JpaRepository<PostAccess, Long>{

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO tb_post_access VALUES (:fkUser, :fkDonation, :fkCampaign, :accessTime)",
            nativeQuery = true
    )
    void savePostAccess(@Param("fkUser") Long fkUser, @Param("fkDonation") Long fkDonation,
                        @Param("fkCampaign") Long fkCampaign, @Param("accessTime") Timestamp accessTime);


    @Query(
            value = "SELECT COUNT(*) FROM tb_post_access WHERE fk_donation = :donationId",
            nativeQuery = true
    )
    Long countByFkDonation(@Param(value = "donationId") Long donationId);

    @Query(
            value = "SELECT COUNT(*) FROM tb_post_access WHERE fk_campaign = :campaignId",
            nativeQuery = true
    )
    Long countByFkCampaign(@Param(value = "campaignId") Long campaignId);
}

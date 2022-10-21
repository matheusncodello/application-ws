package com.matheusncodello.applicationws.infra.database;

import com.matheusncodello.applicationws.domain.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByFkUser(Long id);

    @Query(value =
            "SELECT * FROM tb_donation AS tbd \n" +
            "JOIN tb_favorite_donation AS tfd ON tbd.donation_id = tfd.fk_donation \n" +
            "WHERE tfd.fk_ong = :ongId", nativeQuery = true)
    List<Donation> getFavoriteDonation(@Param("ongId") Long ongId);
}

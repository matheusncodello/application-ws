package com.sptech.applicationws.service.donation;

import com.sptech.applicationws.controllers.dto.request.DonationRequestDTO;
import com.sptech.applicationws.controllers.dto.request.EditDonationRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.sptech.applicationws.controllers.dto.response.DonationResponseDTO;

import java.util.List;

public interface DonationService {

    String createDonation(DonationRequestDTO donation);

    List<DonationResponseDTO> getActiveDonation();

    List<DonationResponseDTO> getUserDonation(Long userId);

    DonationResponseDTO getSingleDonation(Long donationId);

    List<DonationResponseDTO> getFavoriteDonation(Long ongId);

    String editDonation(Long id, EditDonationRequestDTO donation);

    String deleteDonation(Long id);

    String favoriteDonation(FavoriteRequestDTO favorite);

    String unfavoriteDonation(FavoriteRequestDTO unfavorite);

    String countDonationAccess(Long donationId);
}

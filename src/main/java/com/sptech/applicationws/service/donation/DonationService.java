package com.sptech.applicationws.service.donation;

import com.sptech.applicationws.controllers.dto.request.DonationRequestDTO;
import com.sptech.applicationws.controllers.dto.response.DonationResponseDTO;

import java.util.List;

public interface DonationService {

    String createDonation(DonationRequestDTO donation);

    List<DonationResponseDTO> getAllDonation();
}

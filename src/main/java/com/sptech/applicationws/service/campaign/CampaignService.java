package com.sptech.applicationws.service.campaign;

import com.sptech.applicationws.controllers.dto.request.CampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;

import java.util.List;

public interface CampaignService {

    String createCampaign(CampaignRequestDTO campaign);

    List<CampaignResponseDTO> getAllCampaign();
}

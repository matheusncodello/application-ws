package com.sptech.applicationws.service.campaign;

import com.sptech.applicationws.controllers.dto.request.CampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.request.EditCampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;

import java.util.List;

public interface CampaignService {

    String createCampaign(CampaignRequestDTO campaign);

    List<CampaignResponseDTO> getActiveCampaign();
    List<CampaignResponseDTO> getPilha();
    List<CampaignResponseDTO> getOngCampaign(Long ongId);

    CampaignResponseDTO getSingleCampaign(Long campaignId);

    List<CampaignResponseDTO> getFavoriteCampaign(Long userId);

    String editCampaign(Long id, EditCampaignRequestDTO campaign);

    String deleteCampaign(Long id);

    String favoriteCampaign(FavoriteRequestDTO favorite);

    String unfavoriteCampaign(FavoriteRequestDTO unfavorite);

    Long countCampaignAccess(Long campaignId);

    Object[] getFila();
}

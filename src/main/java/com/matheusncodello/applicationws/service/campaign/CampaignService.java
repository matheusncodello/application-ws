package com.matheusncodello.applicationws.service.campaign;

import com.matheusncodello.applicationws.controllers.dto.request.EditCampaignRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.matheusncodello.applicationws.controllers.dto.request.CampaignRequestDTO;

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

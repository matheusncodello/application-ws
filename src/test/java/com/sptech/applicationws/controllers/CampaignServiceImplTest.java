package com.sptech.applicationws.controllers;

import com.sptech.applicationws.controllers.dto.request.EditCampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.sptech.applicationws.domain.Campaign;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.database.*;
import com.sptech.applicationws.service.campaign.impl.CampaignServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CampaignServiceImpl.class})
class CampaignServiceImplTest {

    @Autowired
    CampaignServiceImpl campaignController;

    @MockBean
    @Autowired
    private CampaignRepository campaignRepository;
    @MockBean
    @Autowired
    private FavoriteCampaignRepository favoriteCampaignRepository;
    @MockBean
    @Autowired
    private UserRepository userRepository;
    @MockBean
    @Autowired
    private AddressRepository addressRepository;
    @MockBean
    @Autowired
    private PostAccessRepository postAccessRepository;

    Campaign c1 = new Campaign();
    Campaign c2 = new Campaign();
    Campaign c3 = new Campaign();


    @Test
    void getActiveCampaignIdInvalid() {
        when(campaignRepository.findAll()).thenReturn(new ArrayList<>());

        List<CampaignResponseDTO> result = campaignController.getActiveCampaign();

        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void getOngCampaignIdInvalid() {
        when(campaignRepository.findByFkOng(1L)).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), campaignController.getOngCampaign(1L));
    }


    @Test
    void getSingleCampaignIdInvalid() {
        when(campaignRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {campaignController.getSingleCampaign(1L);});
    }

    @Test
    void getFavoriteCampaignIdInvalid() {
        when(campaignRepository.getFavoriteCampaign(1L)).thenReturn(List.of(c1,c2,c3));

        assertThrows(NotFoundException.class, () -> {campaignController.getFavoriteCampaign(1L);});
    }

    @Test
    void editCampaignIdInvalid() {
        when(campaignRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {campaignController.editCampaign(1L, new EditCampaignRequestDTO());});
    }

    @Test
    void deleteCampaignIdInvalid() {
        when(campaignRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {campaignController.deleteCampaign(1L);});
    }

    @Test
    void favoriteCampaignIdsInvalid() {
        when(userRepository.findById(1L)).thenThrow(NotFoundException.class);
        when(campaignRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {campaignController.favoriteCampaign(new FavoriteRequestDTO(1L, 1L));});
    }

    @Test
    void unfavoriteCampaignIdsInvalid() {
        when(userRepository.findById(1L)).thenThrow(NotFoundException.class);
        when(campaignRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {campaignController.unfavoriteCampaign(new FavoriteRequestDTO(1L, 1L));});
    }

    @Test
    void countCampaignAccessIdInvalid() {
        when(campaignRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> {campaignController.countCampaignAccess(1L);});
    }
}
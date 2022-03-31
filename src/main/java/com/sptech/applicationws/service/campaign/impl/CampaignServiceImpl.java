package com.sptech.applicationws.service.campaign.impl;

import com.sptech.applicationws.controllers.dto.request.CampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.Address;
import com.sptech.applicationws.domain.Campaign;
import com.sptech.applicationws.domain.User;
import com.sptech.applicationws.infra.database.AddressRepository;
import com.sptech.applicationws.infra.database.CampaignRepository;
import com.sptech.applicationws.infra.database.UserRepository;
import com.sptech.applicationws.service.campaign.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private UserRepository ongRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String createCampaign(CampaignRequestDTO campaign) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateFormatted = Timestamp.valueOf(sdf.format(new Date()));

        campaignRepository.save(new Campaign(campaign.getFkOng(), campaign.getCampaignName(), campaign.getCampaignDescription(), dateFormatted, campaign.getCampaignType().getDescription(), campaign.getCampaignImage()));
        return "Campaign registered successfully.";
    }

    @Override
    public List<CampaignResponseDTO> getAllCampaign() {
        List<Campaign> campaignList = campaignRepository.findAll();
        List<CampaignResponseDTO> result = new ArrayList<>();

        for (Campaign d : campaignList) {
            CampaignResponseDTO campaignResponse = new CampaignResponseDTO(
                    d.getCampaignId(),
                    toResponseDTO(d.getFkOng()),
                    d.getCampaignName(),
                    d.getCampaignDescription(),
                    d.getCampaignType(),
                    d.getCampaignImage()
            );

            result.add(campaignResponse);
        }

        return result;
    }

    private UserResponseDTO toResponseDTO(Long fkOng) {
        User ong = ongRepository.findById(fkOng).get();
        Optional<Address> fullAddress = addressRepository.findById(ong.getUserId());
        return new UserResponseDTO(
                ong.getUserId(),
                ong.getUsername(),
                ong.getDocument(),
                new AddressResponseDTO(
                        fullAddress.get().getCep(),
                        fullAddress.get().getStreet(),
                        fullAddress.get().getStreetNumber(),
                        fullAddress.get().getStreetComplement(),
                        fullAddress.get().getDistrict(),
                        fullAddress.get().getCity(),
                        fullAddress.get().getState()
                ),
                ong.getPhoneNumber(),
                ong.getEmail()
        );
    }
}

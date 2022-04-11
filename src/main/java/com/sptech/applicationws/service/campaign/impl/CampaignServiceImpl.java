package com.sptech.applicationws.service.campaign.impl;

import com.sptech.applicationws.controllers.dto.request.CampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.Address;
import com.sptech.applicationws.domain.Campaign;
import com.sptech.applicationws.domain.FavoriteCampaign;
import com.sptech.applicationws.domain.User;
import com.sptech.applicationws.domain.model.FavoriteCampaignId;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.database.AddressRepository;
import com.sptech.applicationws.infra.database.CampaignRepository;
import com.sptech.applicationws.infra.database.FavoriteCampaignRepository;
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
    private FavoriteCampaignRepository favoriteCampaignRepository;

    @Autowired
    private UserRepository ongRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String createCampaign(CampaignRequestDTO campaign) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateFormatted = Timestamp.valueOf(sdf.format(new Date()));

        campaignRepository.save(new Campaign(
                campaign.getFkOng(),
                campaign.getCampaignName(),
                campaign.getCampaignDescription(),
                dateFormatted,
                campaign.getCampaignType().getDescription(),
                campaign.getCampaignImage()
        ));

        return "Campanha registrada com sucesso.";
    }

    @Override
    public List<CampaignResponseDTO> getActiveCampaign() {
        List<Campaign> campaignList = campaignRepository.findAll();
        List<CampaignResponseDTO> result = new ArrayList<>();

        for (Campaign c : campaignList) {
            if (c.getActive()) {
                CampaignResponseDTO campaignResponse = new CampaignResponseDTO(
                        c.getCampaignId(),
                        toResponseDTO(c.getFkOng()),
                        c.getCampaignName(),
                        c.getCampaignDescription(),
                        c.getCampaignType(),
                        c.getCampaignImage()
                );

                result.add(campaignResponse);
            }
        }

        return result;
    }

    @Override
    public List<CampaignResponseDTO> getOngCampaign(Long ongId) {
        List<Campaign> campaignList = campaignRepository.findByFkOng(ongId);
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

    @Override
    public CampaignResponseDTO getSingleCampaign(Long campaignId) {
        Optional<Campaign> foundCampaign = campaignRepository.findById(campaignId);

        if (foundCampaign.isPresent()) {
            Campaign c = foundCampaign.get();

            return new CampaignResponseDTO(
                    c.getCampaignId(),
                    toResponseDTO(c.getFkOng()),
                    c.getCampaignName(),
                    c.getCampaignDescription(),
                    c.getCampaignType(),
                    c.getCampaignImage()
            );
        }

        throw new NotFoundException("Campanha não foi achada.");
    }

    @Override
    public List<CampaignResponseDTO> getFavoriteCampaign(Long userId) {
        return null;
    }

    @Override
    public String editCampaign(Long id, CampaignRequestDTO newCampaign) {
        Optional<Campaign> foundCampaign = campaignRepository.findById(id);

        if (foundCampaign.isPresent()) {
            Campaign c = foundCampaign.get();

            c.setCampaignName(newCampaign.getCampaignName());
            c.setCampaignDescription(newCampaign.getCampaignDescription());
            c.setCampaignImage(newCampaign.getCampaignImage());
            c.setCampaignType(newCampaign.getCampaignType().toString());

            campaignRepository.save(c);

            return "Campanha atualizada com sucesso .";
        }

        return "A campanha que você quer editar não foi encontrada.";
    }

    @Override
    public String deleteCampaign(Long id) {
        Optional<Campaign> foundCampaign = campaignRepository.findById(id);

        if (foundCampaign.isPresent()) {
            Campaign campaign = foundCampaign.get();

            campaign.setActive(false);
            campaignRepository.save(campaign);

            return "Campanha encerrada com sucesso.";
        }

        return "A campanha que você quer encerrar não foi encontrada.";
    }

    @Override
    public String favoriteCampaign(FavoriteRequestDTO favorite) {
        favoriteCampaignRepository.save(
                new FavoriteCampaign(
                        new FavoriteCampaignId(
                                favorite.getUserId(),
                                favorite.getPostId()
                        )
                )
        );

        return "Campanha favoritada com sucesso.";
    }

    @Override
    public String unfavoriteCampaign(FavoriteRequestDTO unfavorite) {
        favoriteCampaignRepository.delete(
                new FavoriteCampaign(
                        new FavoriteCampaignId(
                                unfavorite.getUserId(),
                                unfavorite.getPostId()
                        )
                )
        );

        return "Campanha desfavoritada com sucesso.";
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

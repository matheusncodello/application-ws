package com.matheusncodello.applicationws.service.campaign.impl;

import com.matheusncodello.applicationws.controllers.dto.request.EditCampaignRequestDTO;
import com.matheusncodello.applicationws.domain.Address;
import com.matheusncodello.applicationws.domain.Campaign;
import com.matheusncodello.applicationws.domain.FavoriteCampaign;
import com.matheusncodello.applicationws.domain.User;
import com.matheusncodello.applicationws.controllers.dto.request.CampaignRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.response.AddressResponseDTO;
import com.matheusncodello.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.matheusncodello.applicationws.controllers.dto.response.UserResponseDTO;
import com.matheusncodello.applicationws.infra.database.*;
import com.matheusncodello.applicationws.domain.helpers.FavoriteCampaignId;
import com.matheusncodello.applicationws.infra.configurations.exception.NotFoundException;
import com.matheusncodello.applicationws.service.campaign.CampaignService;
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
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PostAccessRepository postAccessRepository;

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
                        c.getCampaignTime().toString(),
                        c.getCampaignImage(),
                        c.getActive()
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

        for (Campaign c : campaignList) {
            CampaignResponseDTO campaignResponse = new CampaignResponseDTO(
                    c.getCampaignId(),
                    toResponseDTO(c.getFkOng()),
                    c.getCampaignName(),
                    c.getCampaignDescription(),
                    c.getCampaignType(),
                    c.getCampaignTime().toString(),
                    c.getCampaignImage(),
                    c.getActive()
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
                    c.getCampaignTime().toString(),
                    c.getCampaignImage(),
                    c.getActive()
            );
        }

        throw new NotFoundException("Campanha não foi achada.");
    }

    @Override
    public List<CampaignResponseDTO> getFavoriteCampaign(Long userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isEmpty()) {
            throw new NotFoundException("O ID do usuário não foi encontrado.");
        }

        List<Campaign> favoriteCampaigns = campaignRepository.getFavoriteCampaign(userId);
        List<CampaignResponseDTO> result = new ArrayList<>();

        for (Campaign c : favoriteCampaigns) {
            CampaignResponseDTO campaignResponse = new CampaignResponseDTO(
                    c.getCampaignId(),
                    toResponseDTO(c.getFkOng()),
                    c.getCampaignName(),
                    c.getCampaignDescription(),
                    c.getCampaignType(),
                    c.getCampaignTime().toString(),
                    c.getCampaignImage(),
                    c.getActive()
            );

            result.add(campaignResponse);
        }

        return result;
    }

    @Override
    public String editCampaign(Long id, EditCampaignRequestDTO newCampaign) {
        Optional<Campaign> foundCampaign = campaignRepository.findById(id);

        if (foundCampaign.isPresent()) {
            Campaign c = foundCampaign.get();

            c.setCampaignName(newCampaign.getCampaignName());
            c.setCampaignDescription(newCampaign.getCampaignDescription());
            c.setCampaignImage(newCampaign.getCampaignImage());
            c.setCampaignType(newCampaign.getCampaignType().getDescription());

            campaignRepository.save(c);

            return "Campanha atualizada com sucesso .";
        }

        throw new NotFoundException("A campanha que você quer editar não foi encontrada.");
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

        throw new NotFoundException("A campanha que você quer encerrar não foi encontrada.");
    }

    @Override
    public String favoriteCampaign(FavoriteRequestDTO favorite) {
        Optional<User> foundUser = userRepository.findById(favorite.getUserId());
        Optional<Campaign> foundCampaign = campaignRepository.findById(favorite.getPostId());

        if (foundUser.isPresent() && foundCampaign.isPresent()) {
            favoriteCampaignRepository.save(
                    new FavoriteCampaign(
                            new FavoriteCampaignId(
                                    favorite.getUserId(),
                                    favorite.getPostId()
                            )
                    )
            );

            return "Doação favoritada com sucesso.";
        } else if (foundUser.isPresent()) {
            throw new NotFoundException("O ID da campanha não foi encontrada.");
        } else if (foundCampaign.isPresent()) {
            throw new NotFoundException("O ID do usuário não foi encontrado.");
        }

        throw new NotFoundException("Os ID não foi encontrados.");
    }

    @Override
    public String unfavoriteCampaign(FavoriteRequestDTO unfavorite) {
        Optional<User> foundUser = userRepository.findById(unfavorite.getUserId());
        Optional<Campaign> foundCampaign = campaignRepository.findById(unfavorite.getPostId());

        if (foundUser.isPresent() && foundCampaign.isPresent()) {
            favoriteCampaignRepository.delete(
                    new FavoriteCampaign(
                            new FavoriteCampaignId(
                                    unfavorite.getUserId(),
                                    unfavorite.getPostId()
                            )
                    )
            );

            return "Doação favoritada com sucesso.";
        } else if (foundUser.isPresent()) {
            throw new NotFoundException("O ID da campanha não foi encontrada.");
        } else if (foundCampaign.isPresent()) {
            throw new NotFoundException("O ID do usuário não foi encontrado.");
        }

        return "Campanha desfavoritada com sucesso.";
    }

    @Override
    public Long countCampaignAccess(Long campaignId) {
        Optional<Campaign> foundCampaign = campaignRepository.findById(campaignId);

        if (foundCampaign.isPresent()) {
            Long postAccess = postAccessRepository.countByFkCampaign(campaignId);

            return postAccess;
        }

        throw new NotFoundException("O ID não foi encontrado.");
    }

    private UserResponseDTO toResponseDTO(Long fkOng) {
        User ong = userRepository.findById(fkOng).get();
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
                ong.getEmail(),
                ong.isOng()
        );
    }
}

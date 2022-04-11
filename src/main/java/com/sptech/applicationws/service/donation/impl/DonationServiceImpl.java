package com.sptech.applicationws.service.donation.impl;

import com.sptech.applicationws.controllers.dto.request.DonationRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.DonationResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.*;
import com.sptech.applicationws.domain.Donation;
import com.sptech.applicationws.domain.model.FavoriteCampaignId;
import com.sptech.applicationws.domain.model.FavoriteDonationId;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.database.AddressRepository;
import com.sptech.applicationws.infra.database.DonationRepository;
import com.sptech.applicationws.infra.database.FavoriteDonationRepository;
import com.sptech.applicationws.infra.database.UserRepository;
import com.sptech.applicationws.service.donation.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private FavoriteDonationRepository favoriteDonationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String createDonation(DonationRequestDTO donation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateFormatted = Timestamp.valueOf(sdf.format(new Date()));

        donationRepository.save(new Donation(
                donation.getFkUser(),
                donation.getDonationName(),
                donation.getDonationDescription(),
                dateFormatted,
                donation.getDonationType().getDescription(),
                donation.getDonationImage())
        );

        return "Doação registrada com sucesso.";
    }

    @Override
    public List<DonationResponseDTO> getActiveDonation() {
        List<Donation> donationList = donationRepository.findAll();
        List<DonationResponseDTO> result = new ArrayList<>();

        for(Donation d : donationList){
            if (d.getActive()) {
                DonationResponseDTO donationResponse = new DonationResponseDTO(
                        d.getDonationId(),
                        toResponseDTO(d.getFkUser()),
                        d.getDonationName(),
                        d.getDonationDescription(),
                        d.getDonationType(),
                        d.getDonationImage()
                );

                result.add(donationResponse);
            }
        }

        return result;
    }

    @Override
    public List<DonationResponseDTO> getUserDonation(Long userId) {
        List<Donation> donationList = donationRepository.findByFkUser(userId);
        List<DonationResponseDTO> result = new ArrayList<>();

        for(Donation d : donationList){
            DonationResponseDTO donationResponse = new DonationResponseDTO(
                    d.getDonationId(),
                    toResponseDTO(d.getFkUser()),
                    d.getDonationName(),
                    d.getDonationDescription(),
                    d.getDonationType(),
                    d.getDonationImage()
            );

            result.add(donationResponse);
        }

        return result;
    }

    @Override
    public DonationResponseDTO getSingleDonation(Long donationId) {
        Optional<Donation> foundDonation = donationRepository.findById(donationId);

        if (foundDonation.isPresent()) {
            Donation d = foundDonation.get();

            return new DonationResponseDTO(
                    d.getDonationId(),
                    toResponseDTO(d.getFkUser()),
                    d.getDonationName(),
                    d.getDonationDescription(),
                    d.getDonationType(),
                    d.getDonationImage()
            );
        }

        throw new NotFoundException("Doação não foi achada.");
    }

    @Override
    public List<DonationResponseDTO> getFavoriteDonation(Long ongId) {
        return null;
    }

    @Override
    public String editDonation(Long id, DonationRequestDTO newDonation) {
        Optional<Donation> foundDonation = donationRepository.findById(id);

        if (foundDonation.isPresent()) {
            Donation donation = foundDonation.get();

            donation.setDonationName(newDonation.getDonationName());
            donation.setDonationDescription(newDonation.getDonationDescription());
            donation.setDonationImage(newDonation.getDonationImage());
            donation.setDonationType(newDonation.getDonationType().toString());

            donationRepository.save(donation);

            return "Doação atualizada com sucesso .";

        }

        return "A doação que você quer editar não foi encontrada.";
    }

    @Override
    public String deleteDonation(Long id) {
        Optional<Donation> foundDonation = donationRepository.findById(id);

        if (foundDonation.isPresent()) {
            Donation donation = foundDonation.get();

            donation.setActive(false);
            donationRepository.save(donation);

            return "Doação encerrada com sucesso.";
        }

        return "A doação que você quer encerrar não foi encontrada.";
    }

    @Override
    public String favoriteDonation(FavoriteRequestDTO favorite) {
        favoriteDonationRepository.save(
                new FavoriteDonation(
                        new FavoriteDonationId(
                                favorite.getUserId(),
                                favorite.getPostId()
                        )
                )
        );

        return "Doação favoritada com sucesso.";
    }

    @Override
    public String unfavoriteDonation(FavoriteRequestDTO unfavorite) {
        favoriteDonationRepository.delete(
                new FavoriteDonation(
                        new FavoriteDonationId(
                                unfavorite.getUserId(),
                                unfavorite.getPostId()
                        )
                )
        );

        return "Doação desfavoritada com sucesso.";
    }

    private UserResponseDTO toResponseDTO(Long fkUser){
        User user = userRepository.findById(fkUser).get();
        Optional<Address> fullAddress = addressRepository.findById(user.getUserId());
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getDocument(),
                new AddressResponseDTO(
                        fullAddress.get().getCep(),
                        fullAddress.get().getStreet(),
                        fullAddress.get().getStreetNumber(),
                        fullAddress.get().getStreetComplement(),
                        fullAddress.get().getDistrict(),
                        fullAddress.get().getCity(),
                        fullAddress.get().getState()
                ),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }
}

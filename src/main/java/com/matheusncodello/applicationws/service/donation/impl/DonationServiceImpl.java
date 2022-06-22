package com.matheusncodello.applicationws.service.donation.impl;

import com.matheusncodello.applicationws.controllers.dto.request.EditDonationRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.request.DonationRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.response.AddressResponseDTO;
import com.matheusncodello.applicationws.controllers.dto.response.DonationResponseDTO;
import com.matheusncodello.applicationws.controllers.dto.response.UserResponseDTO;
import com.matheusncodello.applicationws.domain.Address;
import com.matheusncodello.applicationws.domain.FavoriteDonation;
import com.matheusncodello.applicationws.domain.User;
import com.matheusncodello.applicationws.infra.database.*;
import com.sptech.applicationws.domain.*;
import com.matheusncodello.applicationws.domain.Donation;
import com.matheusncodello.applicationws.domain.helpers.FavoriteDonationId;
import com.matheusncodello.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.database.*;
import com.matheusncodello.applicationws.service.donation.DonationService;
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

    @Autowired
    private PostAccessRepository postAccessRepository;

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

        for (Donation d : donationList) {
            if (d.getActive()) {
                DonationResponseDTO donationResponse = new DonationResponseDTO(
                        d.getDonationId(),
                        toResponseDTO(d.getFkUser()),
                        d.getDonationName(),
                        d.getDonationDescription(),
                        d.getDonationType(),
                        d.getDonationTime().toString(),
                        d.getDonationImage(),
                        d.getActive()
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

        for (Donation d : donationList) {
            DonationResponseDTO donationResponse = new DonationResponseDTO(
                    d.getDonationId(),
                    toResponseDTO(d.getFkUser()),
                    d.getDonationName(),
                    d.getDonationDescription(),
                    d.getDonationType(),
                    d.getDonationTime().toString(),
                    d.getDonationImage(),
                    d.getActive()
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
                    d.getDonationTime().toString(),
                    d.getDonationImage(),
                    d.getActive()
            );
        }

        throw new NotFoundException("Doação não foi achada.");
    }

    @Override
    public List<DonationResponseDTO> getFavoriteDonation(Long ongId) {
        Optional<User> foundUser = userRepository.findById(ongId);
        if (foundUser.isEmpty()) {
            throw new NotFoundException("O ID do usuário não foi encontrado.");
        }

        List<Donation> favoriteDonations = donationRepository.getFavoriteDonation(ongId);
        List<DonationResponseDTO> result = new ArrayList<>();

        for (Donation d : favoriteDonations) {
            DonationResponseDTO donationResponse = new DonationResponseDTO(
                    d.getDonationId(),
                    toResponseDTO(d.getFkUser()),
                    d.getDonationName(),
                    d.getDonationDescription(),
                    d.getDonationType(),
                    d.getDonationTime().toString(),
                    d.getDonationImage(),
                    d.getActive()
            );

            result.add(donationResponse);
        }

        return result;
    }

    @Override
    public String editDonation(Long id, EditDonationRequestDTO newDonation) {
        Optional<Donation> foundDonation = donationRepository.findById(id);

        if (foundDonation.isPresent()) {
            Donation donation = foundDonation.get();

            donation.setDonationName(newDonation.getDonationName());
            donation.setDonationDescription(newDonation.getDonationDescription());
            donation.setDonationImage(newDonation.getDonationImage());
            donation.setDonationType(newDonation.getDonationType().getDescription());

            donationRepository.save(donation);

            return "Doação atualizada com sucesso.";

        }

        throw new NotFoundException("A doação que você quer editar não foi encontrada.");
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

        throw new NotFoundException("A doação que você quer encerrar não foi encontrada.");
    }

    @Override
    public String favoriteDonation(FavoriteRequestDTO favorite) {
        Optional<User> foundUser = userRepository.findById(favorite.getUserId());
        Optional<Donation> foundDonation = donationRepository.findById(favorite.getPostId());

        if (foundUser.isPresent() && foundDonation.isPresent()) {
            favoriteDonationRepository.save(
                    new FavoriteDonation(
                            new FavoriteDonationId(
                                    favorite.getUserId(),
                                    favorite.getPostId()
                            )
                    )
            );

            return "Doação favoritada com sucesso.";
        } else if (foundUser.isPresent()) {
            throw new NotFoundException("O ID da doação não foi encontrada.");
        } else if (foundDonation.isPresent()) {
            throw new NotFoundException("O ID do usuário não foi encontrado.");
        }

        throw new NotFoundException("Os ID não foi encontrados.");
    }

    @Override
    public String unfavoriteDonation(FavoriteRequestDTO unfavorite) {
        Optional<User> foundUser = userRepository.findById(unfavorite.getUserId());
        Optional<Donation> foundDonation = donationRepository.findById(unfavorite.getPostId());

        if (foundUser.isPresent() && foundDonation.isPresent()) {
            favoriteDonationRepository.delete(
                    new FavoriteDonation(
                            new FavoriteDonationId(
                                    unfavorite.getUserId(),
                                    unfavorite.getPostId()
                            )
                    )
            );

            return "Doação favoritada com sucesso.";
        } else if (foundUser.isPresent()) {
            throw new NotFoundException("O ID da doação não foi encontrada.");
        } else if (foundDonation.isPresent()) {
            throw new NotFoundException("O ID do usuário não foi encontrado.");
        }

        return "Doação desfavoritada com sucesso.";
    }

    @Override
    public String countDonationAccess(Long donationId) {
        Optional<Donation> foundDonation = donationRepository.findById(donationId);

        if (foundDonation.isPresent()) {
            Long postAccess = postAccessRepository.countByFkDonation(donationId);

            return String.format("Possui %d visualizações.", postAccess);
        }

        throw new NotFoundException("O ID não foi encontrado.");
    }

    private UserResponseDTO toResponseDTO(Long fkUser) {
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
                user.getEmail(),
                user.isOng()
        );
    }
}

package com.sptech.applicationws.service.donation.impl;

import com.sptech.applicationws.controllers.dto.request.DonationRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.DonationResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.Address;
import com.sptech.applicationws.domain.Donation;
import com.sptech.applicationws.domain.User;
import com.sptech.applicationws.infra.database.AddressRepository;
import com.sptech.applicationws.infra.database.DonationRepository;
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
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public String createDonation(DonationRequestDTO donation) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateFormatted = Timestamp.valueOf(sdf.format(new Date()));

        donationRepository.save(new Donation(donation.getFkUser(), donation.getDonationName(), donation.getDonationDescription(), dateFormatted, donation.getDonationType().getDescription(), donation.getDonationImage()));
        return "Donation registered successfully.";
    }

    @Override
    public List<DonationResponseDTO> getAllDonation() {
        List<Donation> donationList = donationRepository.findAll();
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

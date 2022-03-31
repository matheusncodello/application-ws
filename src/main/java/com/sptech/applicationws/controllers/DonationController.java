package com.sptech.applicationws.controllers;

import com.sptech.applicationws.controllers.dto.DefaultResponseEnvelope;
import com.sptech.applicationws.controllers.dto.request.DonationRequestDTO;
import com.sptech.applicationws.controllers.dto.response.DonationResponseDTO;
import com.sptech.applicationws.service.donation.DonationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @ApiOperation(value = "Retorna uma lista de doações.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-donation", produces = "application/json")
    public DefaultResponseEnvelope<List<DonationResponseDTO>> getAllDonation(){
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getAllDonation()
        );
    }

    @ApiOperation(value = "Efetua a criação de uma nova doação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua a criação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/create-donation", produces = "application/json", consumes = "application/json")
    public DefaultResponseEnvelope<String> createDonation(@RequestBody DonationRequestDTO donation){
        return new DefaultResponseEnvelope<>(
                true,
                donationService.createDonation(donation)
        );
    }
}

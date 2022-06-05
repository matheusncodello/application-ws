package com.sptech.applicationws.controllers;

import com.sptech.applicationws.controllers.dto.DefaultResponseEnvelope;
import com.sptech.applicationws.controllers.dto.request.DonationRequestDTO;
import com.sptech.applicationws.controllers.dto.request.EditDonationRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
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

    @ApiOperation(value = "Efetua a criação de uma nova doação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua a criação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/create-donation", produces = "application/json", consumes = "application/json")
    public DefaultResponseEnvelope<String> createDonation(@RequestBody DonationRequestDTO donation) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.createDonation(donation)
        );
    }

    @ApiOperation(value = "Retorna uma lista de doações ativas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-donation", produces = "application/json")
    public DefaultResponseEnvelope<List<DonationResponseDTO>> getAllDonation() {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getActiveDonation()
        );
    }

    @ApiOperation(value = "Retorna uma lista de doações de um usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-user-donation/{userId}", produces = "application/json")
    public DefaultResponseEnvelope<List<DonationResponseDTO>> getUserDonation(@PathVariable Long userId) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getUserDonation(userId)
        );
    }

    @ApiOperation(value = "Retorna os detalhes de uma única campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a doação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-single-donation/{donationId}", produces = "application/json")
    public DefaultResponseEnvelope<DonationResponseDTO> getSingleDonation(@PathVariable Long donationId) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getSingleDonation(donationId)
        );
    }

    @ApiOperation(value = "Retorna uma lista de doações favoritadas por uma ONG.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-favorite-donation/{ongId}", produces = "application/json")
    public DefaultResponseEnvelope<List<DonationResponseDTO>> getFavoriteDonation(@PathVariable Long ongId) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getFavoriteDonation(ongId)
        );
    }

    @ApiOperation(value = "Efetua a edição de uma doação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua a edição com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/edit-donation/{id}", consumes = "application/json")
    public DefaultResponseEnvelope<String> editDonation(@PathVariable Long id, @RequestBody EditDonationRequestDTO donation) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.editDonation(id, donation)
        );
    }

    @ApiOperation(value = "Efetua o encerramento de uma doação.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua o delete com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/end-donation/{id}")
    public DefaultResponseEnvelope<String> deleteDonation(@PathVariable Long id) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.deleteDonation(id)
        );
    }

    @ApiOperation(value = "Favorita a doação escolhida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Favorita a doação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/favorite-donation", consumes = "application/json")
    public DefaultResponseEnvelope<String> favoriteDonation(@RequestBody FavoriteRequestDTO favorite) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.favoriteDonation(favorite)
        );
    }

    @ApiOperation(value = "Desfavorita a doação escolhida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Desfavorita a doação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @DeleteMapping(value = "/unfavorite-donation", consumes = "application/json")
    public DefaultResponseEnvelope<String> unfavoriteDonation(@RequestBody FavoriteRequestDTO favorite) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.unfavoriteDonation(favorite)
        );
    }

    @ApiOperation(value = "Conta quantos acessos a doação possui.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Realiza o método com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/count-donation-access/{donationId}")
    public DefaultResponseEnvelope<String> countDonationAccess(@PathVariable Long donationId) {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.countDonationAccess(donationId)
        );
    }

    @ApiOperation(value = "Traz a doações doações mais recentes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Realiza o método com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-donation-order")
    public DefaultResponseEnvelope<List<DonationResponseDTO>> getDonationOrderByRecents() {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getPilha()
        );
    }

    @ApiOperation(value = "Traz as doações mais antigas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Realiza o método com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-donation-order-oldest")
    public DefaultResponseEnvelope<Object[]> getDonationOrderByOldest() {
        return new DefaultResponseEnvelope<>(
                true,
                donationService.getFila()
        );
    }
}

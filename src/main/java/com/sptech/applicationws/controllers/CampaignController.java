package com.sptech.applicationws.controllers;

import com.sptech.applicationws.controllers.dto.DefaultResponseEnvelope;
import com.sptech.applicationws.controllers.dto.request.CampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.request.EditCampaignRequestDTO;
import com.sptech.applicationws.controllers.dto.request.FavoriteRequestDTO;
import com.sptech.applicationws.controllers.dto.response.CampaignResponseDTO;
import com.sptech.applicationws.service.campaign.CampaignService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @ApiOperation(value = "Efetua a criação de uma nova campanhas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua a criação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/create-campaign", produces = "application/json", consumes = "application/json")
    public DefaultResponseEnvelope<String> createCampaign(@RequestBody CampaignRequestDTO campaign) {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.createCampaign(campaign)
        );
    }

    @ApiOperation(value = "Retorna uma lista de campanhas ativas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-campaign", produces = "application/json")
    public DefaultResponseEnvelope<List<CampaignResponseDTO>> getActiveCampaign() {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.getActiveCampaign()
        );
    }

    @ApiOperation(value = "Retorna uma lista de campanhas de uma ONG.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-ong-campaign/{ongId}", produces = "application/json")
    public DefaultResponseEnvelope<List<CampaignResponseDTO>> getOngCampaign(@PathVariable Long ongId) {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.getOngCampaign(ongId)
        );
    }

    @ApiOperation(value = "Retorna os detalhes de uma única campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a campanha com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-single-campaign/{campaignId}", produces = "application/json")
    public DefaultResponseEnvelope<CampaignResponseDTO> getSingleCampaign(@PathVariable Long campaignId) {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.getSingleCampaign(campaignId)
        );
    }

    @ApiOperation(value = "Retorna uma lista de campanhas favoritadas por um usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-favorite-campaign/{userId}", produces = "application/json")
    public DefaultResponseEnvelope<List<CampaignResponseDTO>> getFavoriteCampaign(@PathVariable Long userId) {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.getFavoriteCampaign(userId)
        );
    }

    @ApiOperation(value = "Efetua a edição de uma campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua a edição com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PutMapping(value = "/edit-campaign/{id}", consumes = "application/json")
    public DefaultResponseEnvelope<String> editCampaign(@PathVariable Long id, @RequestBody EditCampaignRequestDTO campaign) {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.editCampaign(id, campaign)
        );
    }

    @ApiOperation(value = "Efetua o encerramento de uma campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua o encerramento com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PutMapping(value = "/end-campaign/{id}")
    public DefaultResponseEnvelope<String> deleteCampaign(@PathVariable Long id) {
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.deleteCampaign(id)
        );
    }

    @ApiOperation(value = "Favorita a campanha escolhida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Favorita a campanha com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/favorite-campaign", consumes = "application/json")
    public DefaultResponseEnvelope<String> favoriteCampaign(@RequestBody FavoriteRequestDTO favorite){
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.favoriteCampaign(favorite)
        );
    }

    @ApiOperation(value = "Desfavorita a campanha escolhida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Desfavorita a campanha com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @DeleteMapping(value = "/unfavorite-campaign", consumes = "application/json")
    public DefaultResponseEnvelope<String> unfavoriteCampaign(@RequestBody FavoriteRequestDTO favorite){
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.unfavoriteCampaign(favorite)
        );
    }
}

package com.sptech.applicationws.controllers;

import com.sptech.applicationws.controllers.dto.DefaultResponseEnvelope;
import com.sptech.applicationws.controllers.dto.request.CampaignRequestDTO;
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

    @ApiOperation(value = "Retorna uma lista de campanhas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-campaign", produces = "application/json")
    public DefaultResponseEnvelope<List<CampaignResponseDTO>> getCampaign(){
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.getAllCampaign()
        );
    }

    @ApiOperation(value = "Efetua a criação de uma nova campanhas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua a criação com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "create-campaign", produces = "application/json", consumes = "application/json")
    public DefaultResponseEnvelope<String> createCampaign(@RequestBody CampaignRequestDTO campaign){
        return new DefaultResponseEnvelope<>(
                true,
                campaignService.createCampaign(campaign)
        );
    }
}

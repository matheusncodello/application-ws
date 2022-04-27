package com.sptech.applicationws.controllers;

import com.sptech.applicationws.controllers.dto.DefaultResponseEnvelope;
import com.sptech.applicationws.controllers.dto.request.LoginRequestDTO;
import com.sptech.applicationws.controllers.dto.request.PostAccessRequestDTO;
import com.sptech.applicationws.controllers.dto.request.UserRegisterRequestDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.PostAccess;
import com.sptech.applicationws.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Efetua o registro do usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua o registro com sucesso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
    public DefaultResponseEnvelope<String> register(@RequestBody UserRegisterRequestDTO user){
        return new DefaultResponseEnvelope<>(
                true,
                userService.register(user)
        );
    }

    @ApiOperation(value = "Efetua o login do usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua o login com sucesso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    public DefaultResponseEnvelope<UserResponseDTO> login(@RequestBody LoginRequestDTO user){
        return new DefaultResponseEnvelope<>(
                true,
                userService.login(user)
        );
    }

    @ApiOperation(value = "Efetua o logoff do usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua o logoff com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/logoff", produces = "application/json")
    public DefaultResponseEnvelope<String> logoff(){
        return new DefaultResponseEnvelope<>(
                true,
                userService.logoff()
        );
    }

    @ApiOperation(value = "Efetua o log de acessos de um post a partir de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Efetua o acesso com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @PostMapping(value = "/register-post-access", consumes = "application/json")
    public DefaultResponseEnvelope<String> registerPostAccess(@RequestBody PostAccessRequestDTO postAccess){
        return new DefaultResponseEnvelope<>(
                true,
                userService.registerPostAccess(postAccess)
        );
    }

    @ApiOperation(value = "Gera um relatório com todas as publicações de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Gera o relatório com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso."),
            @ApiResponse(code = 400, message = "Foi gerado um erro/exceção"),
    })
    @GetMapping(value = "/get-post-history/{userId}")
    public DefaultResponseEnvelope<String> getPostHistory(@PathVariable Long userId){
        return new DefaultResponseEnvelope<>(
                true,
                userService.getPostHistory(userId)
        );
    }
}

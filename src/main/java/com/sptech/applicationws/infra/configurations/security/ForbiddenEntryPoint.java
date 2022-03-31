package com.sptech.applicationws.infra.configurations.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sptech.applicationws.controllers.dto.DefaultResponseEnvelope;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForbiddenEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        mapper(response);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        mapper(response);
    }

    private void mapper(HttpServletResponse response) throws IOException{
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(
                mapper.writeValueAsString(
                        new DefaultResponseEnvelope<>(
                                false,
                                "Forbidden."
                        )
                )
        );
    }
}

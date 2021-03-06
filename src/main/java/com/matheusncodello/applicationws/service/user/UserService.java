package com.matheusncodello.applicationws.service.user;

import com.matheusncodello.applicationws.controllers.dto.request.LoginRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.request.PostAccessRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.request.UserRegisterRequestDTO;
import com.matheusncodello.applicationws.controllers.dto.response.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService extends UserDetailsService {

    String register(@RequestBody UserRegisterRequestDTO user);

    UserResponseDTO login(@RequestBody LoginRequestDTO user);

    String logoff();

    String registerPostAccess(PostAccessRequestDTO postAccess);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

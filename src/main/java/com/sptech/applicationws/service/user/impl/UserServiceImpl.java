package com.sptech.applicationws.service.user.impl;

import com.sptech.applicationws.controllers.dto.request.LoginRequestDTO;
import com.sptech.applicationws.controllers.dto.request.PostAccessRequestDTO;
import com.sptech.applicationws.controllers.dto.request.UserRegisterRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.Address;
import com.sptech.applicationws.domain.PostAccess;
import com.sptech.applicationws.domain.User;
import com.sptech.applicationws.infra.configurations.exception.UserExistsException;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.database.AddressRepository;
import com.sptech.applicationws.infra.database.PostAccessRepository;
import com.sptech.applicationws.infra.database.UserRepository;
import com.sptech.applicationws.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PostAccessRepository postAccessRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UserRegisterRequestDTO user) {
        if (userRepository.findByEmail(user.getEmail()) != null || userRepository.findByDocument(user.getDocument()) != null) {
            throw new UserExistsException("Usuário já existente.");
        }

        User newUser = userRepository.save(new User(
                user.getUsername(),
                user.getDocument(),
                user.getPhoneNumber(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.isOng()
        ));

        addressRepository.save(new Address(
                newUser.getUserId(),
                user.getCep(),
                user.getStreet(),
                user.getStreetNumber(),
                user.getStreetComplement(),
                user.getDistrict(),
                user.getCity(),
                user.getState()
        ));

        return "Usuário registrado com sucesso.";
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO user) {
        UserDetails newUser = loadUserByUsername(user.getLogin());
        boolean match = passwordEncoder.matches(user.getPassword(), newUser.getPassword());
        boolean isEmail = user.getLogin().contains("@");

        if(match){
            User loggedUser = (isEmail)
                    ? userRepository.findByEmail(user.getLogin())
                    : userRepository.findByDocument(user.getLogin());
            Optional<Address> fullAddress = addressRepository.findById(loggedUser.getUserId());

            if(loggedUser == null){
                throw new NotFoundException("O usuário não foi encontrado ou credenciais inválidas.");
            } else {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                newUser,
                                null,
                                newUser.getAuthorities()
                        )
                );

                return new UserResponseDTO(
                        loggedUser.getUserId(),
                        loggedUser.getUsername(),
                        loggedUser.getDocument(),
                        new AddressResponseDTO(
                                fullAddress.get().getCep(),
                                fullAddress.get().getStreet(),
                                fullAddress.get().getStreetNumber(),
                                fullAddress.get().getStreetComplement(),
                                fullAddress.get().getDistrict(),
                                fullAddress.get().getCity(),
                                fullAddress.get().getState()
                        ),
                        loggedUser.getPhoneNumber(),
                        loggedUser.getEmail()
                );
            }
        }

        throw new NotFoundException("O usuário não foi encontrado.");
    }

    @Override
    public String logoff() {
        SecurityContextHolder.clearContext();

        return "Logoff do usuário feito com sucesso.";
    }

    @Override
    public String registerPostAccess(PostAccessRequestDTO postAccess) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp dateFormatted = Timestamp.valueOf(sdf.format(new Date()));

        postAccessRepository.save(
                new PostAccess(
                        postAccess.getFkUser(),
                        postAccess.getFkDonation(),
                        postAccess.getFkCampaign(),
                        dateFormatted
                )
        );

        return "Registro efetuado com sucesso.";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean isEmail = username.contains("@");

        Optional<User> loggedUser = Optional.ofNullable((isEmail)
                ? userRepository.findByEmail(username)
                : userRepository.findByDocument(username));

        if(loggedUser.isEmpty()){
            throw new NotFoundException("O usuário não foi encontrado.");
        }

        User user = loggedUser.get();

        String roles = (user.isOng()) ? "ONG" : "USER";

        return org.springframework.security.core.userdetails.User.
                builder()
                .username((isEmail) ? user.getEmail() : user.getDocument())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
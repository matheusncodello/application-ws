package com.sptech.applicationws.service.user.impl;

import com.sptech.applicationws.controllers.dto.request.LoginRequestDTO;
import com.sptech.applicationws.controllers.dto.request.UserRegisterRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.Address;
import com.sptech.applicationws.domain.User;
import com.sptech.applicationws.infra.configurations.exception.UserExistsException;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.database.AddressRepository;
import com.sptech.applicationws.infra.database.UserRepository;
import com.sptech.applicationws.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UserRegisterRequestDTO user) {
        if (userRepository.findByEmail(user.getEmail()) != null || userRepository.findByDocument(user.getDocument()) != null) {
            throw new UserExistsException("User already registered.");
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

        return "User registered successfully.";
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
                throw new NotFoundException("User not found or bad credentials.");
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

        throw new NotFoundException("User not found.");
    }

    @Override
    public String logoff() {
        SecurityContextHolder.clearContext();

        return "User successfully logged out.";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean isEmail = username.contains("@");

        Optional<User> loggedUser = Optional.ofNullable((isEmail)
                ? userRepository.findByEmail(username)
                : userRepository.findByDocument(username));

        if(loggedUser.isEmpty()){
            throw new NotFoundException("User not found.");
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
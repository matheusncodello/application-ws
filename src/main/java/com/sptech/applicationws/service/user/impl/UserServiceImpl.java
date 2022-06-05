package com.sptech.applicationws.service.user.impl;

import com.sptech.applicationws.controllers.dto.request.LoginRequestDTO;
import com.sptech.applicationws.controllers.dto.request.PostAccessRequestDTO;
import com.sptech.applicationws.controllers.dto.request.UserRegisterRequestDTO;
import com.sptech.applicationws.controllers.dto.response.AddressResponseDTO;
import com.sptech.applicationws.controllers.dto.response.UserResponseDTO;
import com.sptech.applicationws.domain.*;
import com.sptech.applicationws.infra.configurations.exception.UserExistsException;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.configurations.mapper.CsvMapper;
import com.sptech.applicationws.infra.configurations.mapper.ListObj;
import com.sptech.applicationws.infra.database.*;
import com.sptech.applicationws.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PostAccessRepository postAccessRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CsvMapper csvMapper;

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
                user.getOng()
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
                        loggedUser.getEmail(),
                        loggedUser.isOng()
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

        postAccessRepository.savePostAccess(
                postAccess.getFkUser(),
                postAccess.getFkDonation(),
                postAccess.getFkCampaign(),
                dateFormatted
        );

        return "Registro efetuado com sucesso.";
    }

    @Override
    public String getPostHistory(Long userId) {
        Optional<User> userFound = userRepository.findById(userId);

        if (userFound.isPresent()) {
            if (userFound.get().isOng()) {
                List<Campaign> listCampaigns = campaignRepository.findByFkOng(userId);
                ListObj<Campaign> listObj = new ListObj<>(listCampaigns.size());

                for (Campaign c: listCampaigns){
                    listObj.toAdd(c);
                }

                csvMapper.writeCampaignCsv(listObj, "campaigns");
            } else {
                List<Donation> listDonations = donationRepository.findByFkUser(userId);
                ListObj<Donation> listObj = new ListObj<>(listDonations.size());

                for (Donation d: listDonations){
                    listObj.toAdd(d);
                }

                csvMapper.writeDonationCsv(listObj, "donations");
            }
        } else {
            throw new NotFoundException("O usuário não foi encontrado.");
        }

        return "Relatório gerado com sucesso";
    }

    public String leArquivoTxt() {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, email, senha, cnpj, tel, cep, rua, num, district, city, state, isOng;
        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;

        try {
            entrada = new BufferedReader(new FileReader(String.valueOf("user.txt")));
        }
        catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo: " + erro);
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0,2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    System.out.println("Tipo do arquivo: " +
                            registro.substring(2,6));
                    System.out.println("data: " +
                            registro.substring(6,25));
                    System.out.println("versao doc: " +
                            registro.substring(25,27));

                } else if (tipoRegistro.equals("01")) {
                    System.out.println("É um registro de trailer");
                    qtdRegDadoGravado= Integer.parseInt(registro.substring(2,7));
                    if (contaRegDadoLido == qtdRegDadoGravado) {
                        System.out.println("Quantidade de registros lidos compatível " +
                                "com a quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos incompatível " +
                                "com a quantidade de registros gravados");
                    }

                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");
                    nome= registro.substring(2,14).trim();
                    System.out.println(nome);
                    cnpj= registro.substring(14,25).trim();
                    System.out.println(cnpj);
                    email= registro.substring(25,42).trim();
                    System.out.println(email);
                    senha= registro.substring(42,53).trim();
                    System.out.println(senha);
                    tel= registro.substring(53,64).trim();
                    System.out.println(tel);
                    cep= registro.substring(64,72).trim();
                    System.out.println(cep);
                    rua= registro.substring(72,84).trim();
                    System.out.println(rua);
                    num= registro.substring(84, 87).trim();
                    System.out.println(num);
                    district= registro.substring(87, 95).trim();
                    System.out.println(district);
                    city= registro.substring(95, 106).trim();
                    System.out.println(city);
                    state= registro.substring(106, 117).trim();
                    System.out.println(state);
                    isOng= registro.substring(117, 124).trim();
                    System.out.println(isOng);
                    contaRegDadoLido++;

                    System.out.println(Boolean.valueOf(isOng));
                    UserRegisterRequestDTO u = new UserRegisterRequestDTO(
                            nome, cnpj, cep, rua, num, "", district, city, state, Long.valueOf(tel), email, senha, Boolean.parseBoolean(isOng
                    ));

                    register(u);
                } else {
                    System.out.println("Tipo de registro inválido");
                }
                // Le o proximo registro
                registro = entrada.readLine();
            }
            entrada.close();

            return "User cadastrado com sucesso";
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler arquivo: " + erro);
        }
        return "Sucesso";
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
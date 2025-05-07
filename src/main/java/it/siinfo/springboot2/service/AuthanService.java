package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.LoginRequest;
import it.siinfo.springboot2.dto.LoginResponse;
import it.siinfo.springboot2.dto.RegisterRequest;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthanService {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public AuthanService (JwtService jwtService,
                          UserService userService,
                          AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login (LoginRequest loginRequest) {

//        Trova l'user tramite userrname e lo autentica prendendosi username e pw dalla login request
        Users user = userService.findUserByUsername (loginRequest.getUsername ());
        authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (loginRequest.getUsername (),
                loginRequest.getPassWord ()));
//        questo pippo
        String pippo = jwtService.generateToken (user);


        return new LoginResponse (pippo);

    }

    public void register (UsersDTO usersDTO) {

        userService.addUser (usersDTO);

    }


}

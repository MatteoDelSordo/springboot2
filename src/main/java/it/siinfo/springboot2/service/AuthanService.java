package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.*;
import it.siinfo.springboot2.eccezioni.InvalidTokenException;
import it.siinfo.springboot2.entity.Users;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthanService {
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    public AuthanService (JwtService jwtService,
                          UserService userService,
                          AuthenticationManager authenticationManager,
                          RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponse login (LoginRequest loginRequest) {

//        Trova l'user tramite username e lo autentica prendendosi username e pw dalla login request
        Users user = userService.findUserByUsername (loginRequest.getUsername ());
        authenticationManager.authenticate (new UsernamePasswordAuthenticationToken (loginRequest.getUsername (),
                loginRequest.getPassWord ()));
//        questo pippo
        String pippo = jwtService.generateToken (user);

        refreshTokenService.createRefreshToken (user.getId ());

        return new LoginResponse (pippo);

    }

    public void register (UsersDTO usersDTO) {

        userService.addUser (usersDTO);

    }

    public RefreshTokenResponseDTO refresh (RefreshTokenRequestDTO refreshTokenRequestDTO) {
        Users user = userService.findUserByUsername (refreshTokenRequestDTO.getUserName ());
        RefreshTokenDto refreshTokenDto = refreshTokenService.findByUserEmail (refreshTokenRequestDTO.getUserName ());
        if (jwtService.validateToken (refreshTokenDto.getToken ())) {
            String token = jwtService.generateToken (user);
            return new RefreshTokenResponseDTO (token);
        } else {
            throw new InvalidTokenException ("Refresh token scaduto, efettuare di nuovo il login");
        }


    }

}

package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.RefreshTokenDto;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.RefreshToken;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.RefreshTokenMapper;
import it.siinfo.springboot2.repository.RefreshTokenRepository;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Value("${jwt.refreshExp}")
    private Long jwtRefreshExp;

    public RefreshTokenService (RefreshTokenRepository refreshTokenRepository,
                                RefreshTokenMapper refreshTokenMapper,
                                UserRepository userRepository,
                                JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenMapper = refreshTokenMapper;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RefreshTokenDto findByToken (String s) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken (s).orElseThrow (ResourceNotFoundException::new);
        return refreshTokenMapper.toRefreshTokenDto (refreshToken);
    }

    public RefreshTokenDto findByUserId (Long id) {

        RefreshToken refreshToken =
                refreshTokenRepository.findByUser_Id (id).orElseThrow (ResourceNotFoundException::new);
        return refreshTokenMapper.toRefreshTokenDto (refreshToken);

    }

    public RefreshTokenDto findByUserEmail (String email) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser_EMail (email).orElseThrow (
                ResourceNotFoundException::new);
        return refreshTokenMapper.toRefreshTokenDto (refreshToken);
    }


    @Transactional
    public RefreshTokenDto createRefreshToken (Long idUtente) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUser_Id (idUtente);
        RefreshToken refreshToken = optionalRefreshToken.orElse (new RefreshToken ());
        Users user = userRepository.findById (idUtente).orElseThrow (() -> new ResourceNotFoundException (
                "Utente non trovato nella creazione del refreshToken"));
        refreshToken.setUser (user);
        refreshToken.setScadenza (Instant.now ().plusMillis (jwtRefreshExp));
        refreshToken.setToken (jwtService.generateRefreshToken (user));
        refreshToken = refreshTokenRepository.save (refreshToken);
        return refreshTokenMapper.toRefreshTokenDto (refreshToken);
    }

    public void deleteRefreshTokenByUserEMail (String email) {
        RefreshToken optionalRefreshToken =
                refreshTokenRepository.findByUser_EMail (email).orElseThrow (() -> new ResourceNotFoundException (
                "Token con user eMail %s non trovato".formatted (email)));

        refreshTokenRepository.delete (optionalRefreshToken);

        System.out.println ("RefresToken eliminato");

    }


}

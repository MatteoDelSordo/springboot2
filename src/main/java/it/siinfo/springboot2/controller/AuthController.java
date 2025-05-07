package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.LoginRequest;
import it.siinfo.springboot2.dto.LoginResponse;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.service.AuthanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthanService service;


    public AuthController (AuthanService service) {
        this.service = service;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok (service.login (loginRequest));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register (@RequestBody UsersDTO usersDTO) {

        service.register (usersDTO);

        return ResponseEntity.ok ("Pippo corretto");
    }

}

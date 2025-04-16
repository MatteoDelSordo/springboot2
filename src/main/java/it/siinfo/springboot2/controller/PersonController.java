package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.entity.Person;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/persona")
public class PersonController {


    @GetMapping(path = "/get_all")
    public List<Person> getAll () {

        return null;
    }

    @GetMapping(path = "/csrf")
    public CsrfToken getCsrfToken (HttpServletRequest request) {
        return (CsrfToken) request.getAttribute ("_csrf");
    }


}

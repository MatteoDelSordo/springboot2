package it.siinfo.springboot2.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {


    @GetMapping(path = "/test")
    public String test (HttpServletRequest request) {
        return "Test" +
                "/n   " + request.getSession ().getId ();
    }


}

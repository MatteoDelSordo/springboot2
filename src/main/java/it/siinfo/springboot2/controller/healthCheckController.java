package it.siinfo.springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "health-check-controller")
public class healthCheckController {

    @GetMapping(path = "/test")
    public void chiamata() {
    }

}

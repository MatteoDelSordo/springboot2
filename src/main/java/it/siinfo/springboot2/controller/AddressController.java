package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.AddressDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/address")
public class AddressController {


    @GetMapping(path = "/list")
    public List<AddressDTO> getAddressList() {
        return null;
    }


}

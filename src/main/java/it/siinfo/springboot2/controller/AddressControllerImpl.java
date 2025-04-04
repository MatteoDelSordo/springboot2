package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/address")
public class AddressControllerImpl {



    AddressService addressService;

    @Autowired
    public AddressControllerImpl(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(path = "/list")
    public List<AddressDTO> getAddressList() {
        return addressService.getAddressList();
    }


    @GetMapping(path = "/addresbyid/{id}")
    public AddressDTO getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }


    @GetMapping(path = "/addressbyuser/{userId}")
    public AddressDTO getAddressByUserId(@PathVariable Long userId) {

        return addressService.getAddressByUserId(userId);
    }

    @PostMapping(path = "/aggiungi/{userId}")
    public void createAddress(@PathVariable Long userId, @Valid @RequestBody AddressDTO addressDTO) {
        addressService.createAddress(userId,addressDTO);
    }

    @PutMapping(path = "/updateaddress/{id}")

    public void updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        addressService.updateAddressById(id, addressDTO);
    }

    @DeleteMapping(path = "/deleteaddress/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddressById(id);
    }





}

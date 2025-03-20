package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.entity.Address;
import it.siinfo.springboot2.mapper.AddressMapper;
import it.siinfo.springboot2.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
@

    public AddressService(AddressMapper addressMapper,
                          AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }


    public List<AddressDTO> getAddressList() {
        List<Address> listdto = addressRepository.findAll();
        return listdto.stream().map(addressMapper::toAddressDto).toList();

    }


}

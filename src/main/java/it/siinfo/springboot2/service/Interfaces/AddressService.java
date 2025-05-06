package it.siinfo.springboot2.service.Interfaces;

import it.siinfo.springboot2.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    public List<AddressDTO> getAddressList ();

    public AddressDTO getAddressById (Long id);

    public AddressDTO getAddressByUserId (Long id);

    public void createAddress (Long id,
                               AddressDTO addressDTO);

    public void updateAddressById (Long id,
                                   AddressDTO addressDTO);

    public void deleteAddressById (Long id);

}

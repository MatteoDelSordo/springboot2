package it.siinfo.springboot2.service;

import com.sun.source.tree.IfTree;
import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.entity.Address;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.AddressMapper;
import it.siinfo.springboot2.repository.AddressRepository;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressMapper addressMapper,
                          AddressRepository addressRepository, UserRepository userRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


    public List<AddressDTO> getAddressList() {
        List<Address> listdto = addressRepository.findAll();

        return listdto.stream().map(addressMapper::toAddressDto).toList();

    }

    public AddressDTO getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new EntityNotFoundException("Indirizzo non trovato");
        }
        Address address = optionalAddress.get();

        return addressMapper.toAddressDto(address);
    }

    public AddressDTO getAddressByUserId(Long id) {

        Optional<Users> usersOptional = userRepository.findById(id);
        if (usersOptional.isEmpty()) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        Users user = usersOptional.get();
        Address address = user.getAddress();

        return addressMapper.toAddressDto(address);
    }

    public void createAddress(AddressDTO addressDTO) {

        Address address = addressMapper.toAddress(addressDTO);
        addressRepository.save(address);
    }

    public void updateAddressById(Long id, AddressDTO addressDTO) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new EntityNotFoundException("Non trovato");
        }
        Address address = optionalAddress.get();
        AddressDTO addressDaModificare = addressMapper.toAddressDto(address);

        addressDaModificare.setCity(addressDTO.getCity());
        addressDaModificare.setCountry(addressDTO.getCountry());
        addressDaModificare.setState(addressDTO.getState());
        addressDaModificare.setStreet(addressDTO.getStreet());
        addressDaModificare.setZipCode(addressDTO.getZipCode());

        Address modificato = addressMapper.toAddress(addressDaModificare);

        addressRepository.save(modificato);

    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }


}

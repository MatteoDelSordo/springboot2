package it.siinfo.springboot2.service;

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
                          AddressRepository addressRepository,
                          UserRepository userRepository) {
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

    public void createAddress(Long id,
                              AddressDTO addressDTO) {
//        if (addressDTO.getCity() == null || addressDTO.getStreet() == null || addressDTO.getState() == null ||
//        addressDTO.getZipCode() == null || addressDTO.getCountry() == null) {
//            throw new IllegalArgumentException("All address fields are required");
//        }
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User con id" + id + "non presente");
        }
        Users user = optionalUser.get();
        Address address = addressMapper.toAddress(addressDTO);
        address.setUser(user);
        user.setAddress(address);
        addressRepository.save(address);
    }

    public void updateAddressById(Long id,
                                  AddressDTO addressDTO) {

        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new EntityNotFoundException("Non trovato");
        }
        Address address = optionalAddress.get();
        AddressDTO addressDaModificare = addressMapper.toAddressDto(address);

        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setZipCode(addressDTO.getZipCode());

        addressRepository.save(address);

    }

    public void deleteAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new EntityNotFoundException("Indirizzo inesistente");
        }
        Address address = optionalAddress.get();
        Users user = address.getUser();
        user.setAddress(null);
        userRepository.save(user);
        addressRepository.deleteById(id);
    }


}

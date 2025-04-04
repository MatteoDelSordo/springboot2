package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Address;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.AddressMapper;
import it.siinfo.springboot2.repository.AddressRepository;
import it.siinfo.springboot2.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private static final Logger log = LoggerFactory.getLogger (AddressService.class);
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService (AddressMapper addressMapper,
                           AddressRepository addressRepository,
                           UserRepository userRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<AddressDTO> getAddressList () {
        try {

            List<Address> listdto = addressRepository.findAll ();
            log.info ("La lista degli indirizzi è stata chiamata{}", listdto);
            return listdto.stream ().map (addressMapper::toAddressDto).toList ();
        } catch (Exception e) {
            log.warn ("Errore nella visualizzazione della lista degli indirizzi {}", e.getMessage ());
            return null;
        }


    }

    @Transactional
    public AddressDTO getAddressById (Long id) {
        Optional<Address> optionalAddress = addressRepository.findById (id);
        if (optionalAddress.isEmpty ()) {
            log.info ("L'indirizzo che è stato chiamato è tornato vuoto");
            throw new ResourceNotFoundException ("Indirizzo non trovato");
        }
        Address address = optionalAddress.get ();

        log.info ("Questo indirizzo {} è stato cercato", address);

        return addressMapper.toAddressDto (address);
    }

    @Transactional
    public AddressDTO getAddressByUserId (Long id) {

        Optional<Users> usersOptional = userRepository.findById (id);
        if (usersOptional.isEmpty ()) {
            log.info ("Qualcosa è andato storto nella ricerca dell indirizzo dell utente con id {} e non è stato possibile trovarlo",id);
            throw new ResourceNotFoundException ("Utente non trovato nella ricerca dell indirizzo");
        }
        Users user = usersOptional.get ();
        Address address = user.getAddress ();

        return addressMapper.toAddressDto (address);
    }

    @Transactional
    public void createAddress (Long id,
                               AddressDTO addressDTO) {
//        if (addressDTO.getCity() == null || addressDTO.getStreet() == null || addressDTO.getState() == null ||
//        addressDTO.getZipCode() == null || addressDTO.getCountry() == null) {
//            throw new IllegalArgumentException("All address fields are required");
//        }
        Optional<Users> optionalUser = userRepository.findById (id);
        if (optionalUser.isEmpty ()) {
            throw new ResourceNotFoundException ("User con id" + id + "non presente, non è possibile creare un " +
                    "indirizzo");
        }
        Users user = optionalUser.get ();
        Address address = addressMapper.toAddress (addressDTO);
        address.setUser (user);
        user.setAddress (address);
        addressRepository.save (address);
    }

    @Transactional
    public void updateAddressById (Long id,
                                   AddressDTO addressDTO) {

        Optional<Address> optionalAddress = addressRepository.findById (id);
        if (optionalAddress.isEmpty ()) {
            throw new ResourceNotFoundException ("Indirizzo non trovato");
        }
        Address address = optionalAddress.get ();
        AddressDTO addressDaModificare = addressMapper.toAddressDto (address);

        address.setCity (addressDTO.getCity ());
        address.setCountry (addressDTO.getCountry ());
        address.setState (addressDTO.getState ());
        address.setStreet (addressDTO.getStreet ());
        address.setZipCode (addressDTO.getZipCode ());

        addressRepository.save (address);

    }

    @Transactional
    public void deleteAddressById (Long id) {
        Optional<Address> optionalAddress = addressRepository.findById (id);
        if (optionalAddress.isEmpty ()) {
            throw new ResourceNotFoundException ("Indirizzo non presente");
        }
        Address address = optionalAddress.get ();
        Users user = address.getUser ();
        user.setAddress (null);
        userRepository.save (user);
        addressRepository.deleteById (id);
    }



}

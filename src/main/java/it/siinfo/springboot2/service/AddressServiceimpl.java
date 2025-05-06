package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Address;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.AddressMapper;
import it.siinfo.springboot2.repository.AddressRepository;
import it.siinfo.springboot2.repository.UserRepository;

import it.siinfo.springboot2.service.Interfaces.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceimpl implements AddressService {

    private static final Logger log = LoggerFactory.getLogger (AddressServiceimpl.class);
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceimpl (AddressMapper addressMapper,
                               AddressRepository addressRepository,
                               UserRepository userRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<AddressDTO> getAddressList () {
        try {
            log.info ("Chiamo la lista degli indirizzi");
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
        log.info ("Cerco l'indirizzo con l'id: {}", id);
        Optional<Address> optionalAddress = addressRepository.findById (id);
        log.info ("L'indirizzo è stato trovato: {}", optionalAddress);
        if (optionalAddress.isEmpty ()) {
            throw new ResourceNotFoundException ("Indirizzo non trovato");
        }
        log.debug ("Trasformo l'optional in entità");
        Address address = optionalAddress.get ();

        log.info ("Questo indirizzo {} è stato mappato", address);

        return addressMapper.toAddressDto (address);
    }

    @Transactional
    public AddressDTO getAddressByUserId (Long id) {
        log.info ("Cerco indirizzo per utente con id: {}", id);
        Optional<Users> usersOptional = userRepository.findById (id);
        if (usersOptional.isEmpty ()) {

            throw new ResourceNotFoundException ("Utente non trovato nella ricerca dell indirizzo");
        }

        Users user = usersOptional.get ();

        Address address = user.getAddress ();
        log.info ("Recuperato indirizzo  dell utente con id {}", id);
        return addressMapper.toAddressDto (address);
    }

    @Transactional
    public void createAddress (Long id,
                               AddressDTO addressDTO) {

        log.info ("Ricerca user con id: {}", id);

        Optional<Users> optionalUser = userRepository.findById (id);
        if (optionalUser.isEmpty ()) {
            throw new ResourceNotFoundException ("User con id" + id + "non presente, non è possibile creare un " +
                    "indirizzo");
        }

        Users user = optionalUser.get ();
        Address address = addressMapper.toAddress (addressDTO);
        log.info ("Aggiungo l'user a address");
        address.setUser (user);
        {
        }
        log.info ("Aggiungo l'address a user");
        log.debug ("User: {}  | Address : {}", user, address);
        user.setAddress (address);
        log.info ("Uscita dal metodo");
        addressRepository.save (address);
    }

    @Transactional
    public void updateAddressById (Long id,
                                   AddressDTO addressDTO) {
        log.info ("cerco l'indirizzo con id: {}", id);
        Optional<Address> optionalAddress = addressRepository.findById (id);
        if (optionalAddress.isEmpty ()) {
            throw new ResourceNotFoundException ("Indirizzo non trovato");
        }
        log.info ("Indirizzo trovato");
        Address address = optionalAddress.get ();
        log.debug ("Inidirizzo con id {} prima della modifica: {}", id, address);
        AddressDTO addressDaModificare = addressMapper.toAddressDto (address);

        addressDaModificare.setCity (addressDTO.getCity ());
        addressDaModificare.setCountry (addressDTO.getCountry ());
        addressDaModificare.setState (addressDTO.getState ());
        addressDaModificare.setStreet (addressDTO.getStreet ());
        addressDaModificare.setZipCode (addressDTO.getZipCode ());

        Address addressModificato = addressMapper.toAddress (addressDaModificare);

        log.debug ("Indirizzo con id : {} dopo la modifica: {}", id, addressModificato);

        addressRepository.save (address);

        log.info ("Inidirizzo modificato con successo");
    }

    @Transactional
    public void deleteAddressById (Long id) {

        log.info ("Recupero l'indirizzo con id {} per eliminarlo", id);
        Optional<Address> optionalAddress = addressRepository.findById (id);
        if (optionalAddress.isEmpty ()) {
            throw new ResourceNotFoundException ("Indirizzo non presente");
        }

        log.debug ("Recupero l'utente associato all indirizzo {}", optionalAddress);
        Address address = optionalAddress.get ();
        Users user = address.getUser ();
        user.setAddress (null);
        log.debug ("L'inidirizzo è stato scollegato dall utente {}", user);

        userRepository.save (user);
        log.debug ("User aggiornato");

        addressRepository.deleteById (id);
        log.info ("Inidirizzo eliminato");
    }


}

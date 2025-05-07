package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.UsersMapper;
import it.siinfo.springboot2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    final UserRepository userRepository;
    private UsersMapper usersMapper;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService (UserRepository userRepository,
                        UsersMapper usersMapper,
                        PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<UsersDTO> getUsers () {
        return usersMapper.toUsersDtoList (userRepository.findAll ());
    }

    @Transactional
    public List<UsersDTO> metodoJpa () {
        return usersMapper.toUsersDtoList (userRepository.findAllByOrderByNameAsc ());
    }

    @Transactional
    public UsersDTO findUserById (Long id) {
        Users users = userRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException ("bho"));

        return usersMapper.toUserDto (users);
    }

    @Transactional
    public UsersDTO addUser (UsersDTO usersDto) {
        usersDto.setPassword (passwordEncoder.encode (usersDto.getPassword ()));
        Users u = usersMapper.toUser (usersDto);
        return usersMapper.toUserDto (userRepository.save (u));

    }

    @Transactional
    public void deleteUserById (Long id) {
        userRepository.deleteById (id);
    }

    @Transactional
    public void updateNameAndEmailUserById (Long id,
                                            UsersDTO userDto) {

        Optional<Users> optionalUsers = userRepository.findById (id);
        if (optionalUsers.isEmpty ()) {
            throw new ResourceNotFoundException ("qualcosa è andato storto");
        }
        Users paolino = optionalUsers.get ();
        paolino.setName (userDto.getName ());
        paolino.seteMail (userDto.geteMail ());
        userRepository.save (paolino);
    }

    @Transactional
    public void resetPwById (Long id,
                             UsersDTO userDto) {

        Optional<Users> optionalUsers = userRepository.findById (id);
        if (optionalUsers.isEmpty ()) {
            throw new ResourceNotFoundException ("qualcosa è andato storto");
        }
        Users paolino = optionalUsers.get ();
        paolino.setPassword (userDto.getPassword ());
    }

    @Transactional
    public List<UsersDTO> getUserOrderedByName () {
        List<Users> orderedList = userRepository.findAll ();

        orderedList =
                orderedList.stream ().sorted (Comparator.comparing (Users::getName)).collect (Collectors.toList ());
        return usersMapper.toUsersDtoList (orderedList);
    }

    @Transactional
    public List<UsersDTO> getUserByName (String name) {
        return usersMapper.toUsersDtoList (userRepository.findByName (name));
    }

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        return userRepository.findByEMail (username).orElseThrow (() -> new ResourceNotFoundException (
                "Utente non trovato"));
    }


    public Users findUserByUsername (String pippo) {

        return userRepository.findByEMail (pippo).orElseThrow (() -> new ResourceNotFoundException ("Pippo non " +
                "trovato"));

    }
}

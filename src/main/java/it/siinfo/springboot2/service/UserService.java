package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    final UserRepository userRepository;
    private ModelMapper mm;

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelMapper mm) {
        this.userRepository = userRepository;
        this.mm = mm;
    }
    @Transactional
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public List<Users>metodoJpa () {
        return userRepository.findAllByOrderByNameAsc();
    }
    @Transactional
    public Users findUserById(Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        return optionalUsers.get();
    }
    @Transactional
    public Users addUser(UsersDTO usersDto) {
        Users user = new Users();
        mm.map(usersDto, user);
        return userRepository.save(user);

    }
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    public void updateNameAndEmailUserById(Long id,
                                           UsersDTO userDto) {

        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            throw new EntityNotFoundException("qualcosa è andato storto");
        }
        Users paolino = optionalUsers.get();
        paolino.setName(userDto.getName());
        paolino.seteMail(userDto.geteMail());
        userRepository.save(paolino);
    }
    @Transactional
    public void resetPwById(Long id,
                            UsersDTO userDto) {

        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            throw new EntityNotFoundException("qualcosa è andato storto");
        }
        Users paolino = optionalUsers.get();
        paolino.setPassword(userDto.getPassword());
    }

    @Transactional
    public List<Users> getUserOrderedByName() {
        List<Users> orderedList = userRepository.findAll();

        orderedList = orderedList.stream().sorted(Comparator.comparing(Users::getName)).collect(Collectors.toList());
        return orderedList;
    }
    @Transactional
    public List<Users> getUserByName(String name) {
        return userRepository.findByName(name);
    }
}

package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Users> getUsers() {
        return userRepository.findAll();
    }


    public List<Users>metodoJpa () {
        return userRepository.findAllByOrderByNameAsc();
    }
    public Users findUserById(Long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        return optionalUsers.get();
    }

    public Users addUser(UsersDTO usersDto) {
        Users user = new Users();
        mm.map(usersDto, user);
        return userRepository.save(user);

    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

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

    public void resetPwById(Long id,
                            UsersDTO userDto) {

        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isEmpty()) {
            throw new EntityNotFoundException("qualcosa è andato storto");
        }
        Users paolino = optionalUsers.get();
        paolino.setPassword(userDto.getPassword());
    }


    public List<Users> getUserOrderedByName() {
        List<Users> orderedList = userRepository.findAll();

        orderedList = orderedList.stream().sorted(Comparator.comparing(Users::getName)).collect(Collectors.toList());
        return orderedList;
    }

    public List<Users> getUserByName(String name) {
        return userRepository.findByName(name);
    }
}

package it.siinfo.springboot2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.controller.interfaces.UserController;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserControllerImpl implements UserController {


    private final UserService userService;

    @Autowired
    public UserControllerImpl (UserService userService) {

        this.userService = userService;
    }


    @GetMapping(path = "/lista")
    public List<Users> getUsers () {
        return userService.getUsers ();
    }


    @GetMapping(path = "/listaordinata")
    public List<Users> getUsersOrderedByName () {
        return userService.getUserOrderedByName ();
    }

    @GetMapping(path = "/listaordinataconjpa")
    public List<Users> metodoStrano () {
        return userService.metodoJpa ();
    }

    @GetMapping(path = "/getbyname/{name}")
    public List<Users> getUsersByName (@PathVariable String name) {
        return userService.getUserByName (name);
    }

    @GetMapping(path = "/utente/{id}")
    public Users getUserById (@PathVariable Long id) {
        return userService.findUserById (id);
    }

    @PostMapping(path = "/add")
    public void createUser (@Valid @RequestBody UsersDTO usersDto) {
        userService.addUser (usersDto);
    }

    @DeleteMapping(path = "/deleteid/{id}")
    public void deleteUserById (@PathVariable Long id) {
        userService.deleteUserById (id);
    }

    @PutMapping(path = "/modifica/{id}")
    public void updateNameAndEmailUserById (@PathVariable Long id,
                                            @RequestBody UsersDTO usersDto) {
        userService.updateNameAndEmailUserById (id, usersDto);
    }

    @PutMapping(path = "/resetpw/{id}")
    public void resetPwById (@PathVariable Long id,
                             @RequestBody UsersDTO usersDto) {
        userService.resetPwById (id, usersDto);
    }


}

package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/lista")
    public List<Users> getUsers(Model model) {
        List<Users> users = userService.getUsers();
        System.out.println("ciao");
        model.addAttribute("users", users);
        return users;
    }

    @GetMapping(path = "/listaordinata")
    public List<Users> getUsersOrderedByName(Model model) {
        List<Users> users = userService.getUserOrderedByName();
        model.addAttribute("users", users);
        return users;
    }

    @GetMapping(path = "/listaordinataconjpa")
    public List<Users> metodoStrano() {

        return userService.metodoJpa();
    }

    @GetMapping(path = "/getbyname/{name}")
    public List<Users> getUsersByName(@PathVariable String name) {

        return userService.getUserByName(name);
    }

    @GetMapping(path = "/utente/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @PostMapping(path = "/add")
    public void createUser(@RequestBody UsersDTO usersDto) {
        Users salvato = userService.addUser(usersDto);
    }

    @DeleteMapping(path = "/deleteid/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping(path = "/modifica/{id}")
    public void updateNameAndEmailUserById(@PathVariable Long id,
                                           @RequestBody UsersDTO usersDto) {
        userService.updateNameAndEmailUserById(id, usersDto);
    }


    @PutMapping(path = "/resetpw/{id}")
    public void resetPwById(@PathVariable Long id,
                            @RequestBody UsersDTO usersDto) {
        userService.resetPwById(id, usersDto);
    }


}

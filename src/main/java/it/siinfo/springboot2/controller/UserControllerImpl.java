package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.controller.interfaces.UserController;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.service.UserService;
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
    public List<UsersDTO> getUsers () {
        return userService.getUsers ();
    }


    @GetMapping(path = "/listaordinata")
    public List<UsersDTO> getUsersOrderedByName () {
        return userService.getUserOrderedByName ();
    }

    @GetMapping(path = "/listaordinataconjpa")
    public List<UsersDTO> metodoStrano () {
        return userService.metodoJpa ();
    }

    @GetMapping(path = "/getbyname/{name}")
    public List<UsersDTO> getUsersByName (@PathVariable String name) {
        return userService.getUserByName (name);
    }

    @GetMapping(path = "/utente/{id}")
    public UsersDTO getUserById (@PathVariable Long id) {
        return userService.findUserById (id);
    }

    @PostMapping(path = "/add")
    public UsersDTO createUser (@RequestBody UsersDTO usersDto) {
//        try {
            return userService.addUser (usersDto);
//        } catch (Exception e) {
//            System.out.println (e.getMessage ());
//            return new UsersDTO ("nothing",
//                    "pippo@gmail.com",
//                    "Lello",
//                    new Timestamp (new Date ().getTime ()),
//                    "12353",
//                    null,
//                    null);
//        }
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

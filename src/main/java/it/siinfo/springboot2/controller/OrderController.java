package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UserANdORderDto;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.service.OrdersService;
import it.siinfo.springboot2.service.UserService;
import jakarta.persistence.PostUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {


    private final OrdersService ordersService;
    private final UserService userService;


    public OrderController(OrdersService ordersService,
                           UserService userService) {
        this.ordersService = ordersService;
        this.userService = userService;
    }

    @GetMapping(path = "/lista")
    public List<OrdersDTO> getOrders(Model model) {
        List<OrdersDTO> orders = ordersService.getOrders();
        model.addAttribute("orders", orders);
        return orders;
    }


    @PostMapping(path = "/addorderanduser")
    public void createOrderAndUser(@RequestBody UserANdORderDto pippo) {

        ordersService.addOrderWithUser(pippo.getOrdersDTO(), pippo.getUsersDTO());


    }

    @PostMapping(path = "/addorder")
    public void addOrder(@RequestBody OrdersDTO ordersDTO) {

        Orders salvato = ordersService.addOrder(ordersDTO);
    }

@PostMapping(path = "/addordertouser/{idUser}")
public void addOrderToUser(@RequestBody OrdersDTO ordersDTO , @PathVariable Long idUser){
    Orders order = ordersService.addOrderToUser(idUser, ordersDTO);
    }




}

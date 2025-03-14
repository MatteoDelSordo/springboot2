package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UserANdORderDto;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.service.OrdersService;
import it.siinfo.springboot2.service.UserService;
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
    public List<Orders> getOrders(Model model) {
        List<Orders> orders = ordersService.getOrders();
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


}

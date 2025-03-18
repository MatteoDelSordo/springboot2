package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UserAndOrderDto;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.service.OrdersService;
import it.siinfo.springboot2.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {


    private final OrdersService ordersService;

    public OrderController(OrdersService ordersService) {

        this.ordersService = ordersService;

    }

    //ritorna la lista completa
    @GetMapping(path = "/lista")
    public List<OrdersDTO> getOrders() {

        return ordersService.getOrders();
    }

@GetMapping(path = "findbyid/{id}")
    public OrdersDTO getOrdersById(@PathVariable Long id,
                                   @RequestBody OrdersDTO ordersDTO ){

        return ordersService.findOrderById(id);
}

    //Crea un user e un ordine da associare
    @PostMapping(path = "/addorderanduser")
    public void createOrderAndUser(@RequestBody UserAndOrderDto pippo) {

        ordersService.addOrderWithUser(pippo.getOrdersDTO(), pippo.getUsersDTO());


    }

// questo non serve piu
//    @PostMapping(path = "/addorder")
//    public void addOrder(@RequestBody OrdersDTO ordersDTO) {
//
//        Orders salvato = ordersService.addOrder(ordersDTO);
//    }


    @PostMapping(path = "/addordertouser/{idUser}")
    public void addOrderToUser(@RequestBody OrdersDTO ordersDTO, @PathVariable Long idUser) {
        Orders order = ordersService.addOrderToUser(idUser, ordersDTO);
    }

    @PutMapping(path = "/update/{id}")
    public void updateById(@RequestBody OrdersDTO ordersDTO,
                           @PathVariable Long id) {

        ordersService.addOrderToUser(id, ordersDTO);

    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id){
        ordersService.deleteById(id);
    }


}

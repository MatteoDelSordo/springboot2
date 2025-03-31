package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.controller.interfaces.OrderController;
import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UserAndOrderDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.mapper.OrdersMapper;
import it.siinfo.springboot2.service.OrdersService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderControllerImpl implements OrderController {


    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;


    public OrderControllerImpl(OrdersService ordersService,
                               OrdersMapper ordersMapper) {

        this.ordersService = ordersService;

        this.ordersMapper = ordersMapper;
    }


    @GetMapping(path = "/lista")
    public List<OrdersDTO> getOrders() {

        return ordersService.getOrders();

    }


    @GetMapping(path = "findbyid/{id}")
    public OrdersDTO getOrdersById(@PathVariable Long id,
                                   @RequestBody OrdersDTO ordersDTO) {

        return ordersService.findOrderById(id);

    }


    @PostMapping(path = "/addorderanduser")
    public void createOrderAndUser(@RequestBody UserAndOrderDTO pippo) {
        System.out.println("Json rivevuto: " + pippo);
        System.out.println("Order: " + pippo.getOrdersDTO());
        System.out.println("User: " + pippo.getUsersDTO());
        ordersService.addOrderWithUser(pippo.getOrdersDTO(), pippo.getUsersDTO());

    }


    @PostMapping(path = "/addordertouser/{idUser}")
    public void addOrderToUser(@RequestBody OrdersDTO ordersDTO,
                               @PathVariable Long idUser) {
        Orders order = ordersService.addOrderToUser(idUser, ordersDTO);
    }


    @GetMapping(path = "/getquery/{id}")
    public List<OrdersDTO> getOrdersByUtenteIdQuery(@PathVariable Long id) {
        return ordersService.getAllByUserIdQuery(id);
    }


    @GetMapping(path = "/getordersofuser/{usersId}")
    public List<OrdersDTO> getOrdersByUtenteId(@PathVariable Long usersId) {
        return ordersService.getOrdersOfUsersById(usersId);
    }


    @PutMapping(path = "/update/{id}")
    public void updateById(@RequestBody OrdersDTO ordersDTO,
                           @PathVariable Long id) {

        ordersService.addOrderToUser(id, ordersDTO);

    }


    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        ordersService.deleteById(id);
    }


    @GetMapping("/paginated")
    public Page<Orders> getAllOrders(@ParameterObject Pageable pageable) {
        return ordersService.getAllOrders(pageable);
    }

    @PutMapping(path = "/associateUtoO/{idUser}/{idOrder}")
    public OrdersDTO associateUserToOrder(@PathVariable Long idUser,
                                          @PathVariable Long idOrder) {

        return ordersMapper.toOrdersDTO(ordersService.addExistingOrderToUser(idUser, idOrder));
    }


}

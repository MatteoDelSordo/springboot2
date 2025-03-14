package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.repository.OrderRepository;
import it.siinfo.springboot2.repository.UserRepository;
import org.apache.catalina.User;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    final OrderRepository orderRepository;
    final UserService userService;
    private ModelMapper mm;

    @Autowired
    public OrdersService(OrderRepository orderRepository,
                         ModelMapper modelMapper,
                         UserService userService) {
        this.orderRepository = orderRepository;
        this.mm = modelMapper;
        this.userService = userService;

    }

    public List<Orders> getOrders() {

        return orderRepository.findAll();
    }

    public Orders findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ordine non trovato"));


    }

    public Orders addOrderWithUser(OrdersDTO ordersDTO,
                                   UsersDTO usersDTO) {
        Users uNuovo = userService.addUser(usersDTO);
        Orders order = mm.map(ordersDTO, Orders.class);
        order.setProduct(ordersDTO.getProduct());
        order.setAmount(ordersDTO.getAmount());
        uNuovo.setOrders(List.of(order));
        return orderRepository.save(order);
    }


    public Orders addOrder(OrdersDTO ordersDTO) {
        Orders order = mm.map(ordersDTO, Orders.class);
        return orderRepository.save(order);
    }
}

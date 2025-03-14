package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.OrdersMapper;
import it.siinfo.springboot2.repository.OrderRepository;
import it.siinfo.springboot2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    final OrderRepository orderRepository;
    final UserService userService;
    private final OrdersMapper ordersMapper;

    private ModelMapper mm;

    @Autowired
    public OrdersService(OrderRepository orderRepository,
                         ModelMapper modelMapper,
                         UserService userService,
                         OrdersMapper ordersMapper) {
        this.orderRepository = orderRepository;
        this.mm = modelMapper;
        this.userService = userService;
        this.ordersMapper = ordersMapper;

    }

    public List<OrdersDTO> getOrders() {
        List<Orders> o = orderRepository.findAll();
        return ordersMapper.toOrdersDTOList(o);
    }

    public Orders findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ordine non trovato"));


    }

    public void addOrderWithUser(OrdersDTO ordersDTO,
                                 UsersDTO usersDTO) {
        Users uNuovo = userService.addUser(usersDTO);
        Orders order = ordersMapper.toOrders(ordersDTO);
//        order.setProduct(ordersDTO.getProduct());
//        order.setAmount(ordersDTO.getAmount());
        order.setUsers(uNuovo);
        orderRepository.save(order);
    }


    public Orders addOrder(OrdersDTO ordersDTO) {
        Orders order = mm.map(ordersDTO, Orders.class);
        return orderRepository.save(order);
    }


    public void modifyById(Long id,
                           OrdersDTO ordersDTO) {
//        Optional<>


    }

    public Orders addOrderToUser(Long idUser,
                                 OrdersDTO ordersDTO) {
        Users user = userService.findUserById(idUser);
        Orders order = mm.map(ordersDTO, Orders.class);
        order.setUsers(user);
        return orderRepository.save(order);

    }
}

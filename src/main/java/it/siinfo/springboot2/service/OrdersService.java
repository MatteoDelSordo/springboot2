package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.OrdersMapper;
import it.siinfo.springboot2.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //Get degli ordini
    @Transactional
    public List<OrdersDTO> getOrders() {
        List<Orders> o = orderRepository.findAll();
        return ordersMapper.toOrdersDTOList(o);
    }

    //Get di un ordine specifico tramite id
    @Transactional
    public OrdersDTO findOrderById(Long id) {

        Orders orderDaMappare = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Ordine non trovato"));

        return ordersMapper.toOrdersDTO(orderDaMappare);


    }

    //Aggiunge un ordine e crea un user
    @Transactional
    public void addOrderWithUser(OrdersDTO ordersDTO,
                                 UsersDTO usersDTO) {
        Users uNuovo = userService.addUser(usersDTO);
        Orders order = ordersMapper.toOrders(ordersDTO);
//        order.setProduct(ordersDTO.getProduct());
//        order.setAmount(ordersDTO.getAmount());
        order.setUsers(uNuovo);
        orderRepository.save(order);
    }

    // questo serviva principalmente per vedere se funznionava l'add dell ordine, è virtualmente inutile ora.
    @Transactional
    public Orders addOrder(OrdersDTO ordersDTO) {
        Orders order = mm.map(ordersDTO, Orders.class);
        return orderRepository.save(order);
    }

    //Modifica tramite id
    @Transactional
    public void modifyById(Long id,
                           OrdersDTO ordersDTO) {

        Orders franco = orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entita non trovata"));

        franco.setProduct(ordersDTO.getProduct());
        franco.setAmount(ordersDTO.getAmount());
        franco.setProductType(ordersDTO.getProductType());
        orderRepository.save(franco);


    }
    @Transactional
    public Orders addOrderToUser(Long idUser,
                                 OrdersDTO ordersDTO) {
        Users user = userService.findUserById(idUser);
        Orders order = mm.map(ordersDTO, Orders.class);
        order.setUsers(user);
        return orderRepository.save(order);

    }

    @Transactional
    public void deleteById(Long id) {
//        Optional<Orders> optionalOrders = orderRepository.findById(id);
//        Orders orders = optionalOrders.get();
        orderRepository.deleteById(id);
    }

    @Transactional
    public List<OrdersDTO> getOrdersOfUsersById(Long userId) {

        List<Orders> ordersList = orderRepository.findAllByUsers_Id(userId);

        return ordersList.stream().map(ordersMapper::toOrdersDTO).toList();

    }
    @Transactional
    public List<OrdersDTO> getAllByUserIdQuery(Long id) {
        List<Orders> ordersList = orderRepository.findAllByUserIdQuerySchiantata(id);
        System.out.println(ordersList);
        return ordersList.stream().map(ordersMapper::toOrdersDTO).toList();

    }
    @Transactional
    public Page<Orders> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}

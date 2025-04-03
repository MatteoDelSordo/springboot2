package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.OrdersMapper;
import it.siinfo.springboot2.mapper.UsersMapper;
import it.siinfo.springboot2.repository.OrderRepository;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersService {

    private static final Logger log = LoggerFactory.getLogger (OrdersService.class);
    final OrderRepository orderRepository;
    final UserRepository userRepository;
    private final OrdersMapper ordersMapper;
    private final UsersMapper usersMapper;


    @Autowired
    public OrdersService (OrderRepository orderRepository,
                          UserRepository userRepository,
                          OrdersMapper ordersMapper,
                          UsersMapper usersMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.ordersMapper = ordersMapper;

        this.usersMapper = usersMapper;
    }

    //Get degli ordini
    @Transactional
    public List<OrdersDTO> getOrders () {
        List<Orders> o = orderRepository.findAll ();
        return ordersMapper.toOrdersDTOList (o);
    }

    //Get di un ordine specifico tramite id
    @Transactional
    public OrdersDTO findOrderById (Long id) {

        Orders orderDaMappare = orderRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Ordine con id: " + id + " non trovato"));

        return ordersMapper.toOrdersDTO (orderDaMappare);


    }

    //Aggiunge un ordine e crea un user
    @Transactional
    public void addOrderWithUser (OrdersDTO ordersDTO,
                                  UsersDTO usersDTO) {
        Users user = usersMapper.toEntity (usersDTO);
        Users userPerOrdine = userRepository.save (user);
        Orders order = ordersMapper.toOrders (ordersDTO);

        order.setUsers (userPerOrdine);
        orderRepository.save (order);
    }

    // questo serviva principalmente per vedere se funznionava l'add dell ordine, è virtualmente inutile ora.
    @Transactional
    public void addOrder (OrdersDTO ordersDTO) {
        Orders order = ordersMapper.toOrders (ordersDTO);
        orderRepository.save (order);
    }

    //Modifica tramite id
    @Transactional
    public void modifyById (Long id,
                            OrdersDTO ordersDTO) {

        Orders franco = orderRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Ordine con id: 2" + id + " non trovato"));

        franco.setProduct (ordersDTO.getProduct ());
        franco.setAmount (ordersDTO.getAmount ());
        franco.setProductType (ordersDTO.getProductType ());

        orderRepository.save (franco);


    }

    @Transactional
    public Orders addOrderToUser (Long idUser,
                                  OrdersDTO ordersDTO) {
        Users user =
                userRepository.findById (idUser).orElseThrow (() -> new ResourceNotFoundException ("User con " + "id" + ":" + " " + idUser + " non trovato"));
        Orders order = ordersMapper.toOrders (ordersDTO);
        order.setUsers (user);
        return orderRepository.save (order);

    }

    @Transactional
    public Orders addExistingOrderToUser (Long idUser,
                                          Long idOrder) {
        Users user =
                userRepository.findById (idUser).orElseThrow (() -> new ResourceNotFoundException ("User con " + "id" + ":" + " " + idUser + " non trovato"));
        Orders order = orderRepository.findById (idOrder).orElseThrow (() -> new ResourceNotFoundException (
                "Ordine non trovato"));
        order.setUsers (user);
        return orderRepository.save (order);

    }

    @Transactional
    public void deleteById (Long id) {
//        Optional<Orders> optionalOrders = orderRepository.findById(id);
//        Orders orders = optionalOrders.get();
        try {
            Orders orders = orderRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                    "L'ordine non è presente nel database degli ordini"));

            orderRepository.delete (orders);

        } catch (Exception exception) {
            System.out.println ("Qualcosa è andato storto nell eliminazione dell ordine con id: " + id);

        }
    }

    @Transactional
    public List<OrdersDTO> getOrdersOfUsersById (Long userId) {

        try {

            Users users = userRepository.findById (userId).orElseThrow (() -> new ResourceNotFoundException (
                    "Utente con id: " + userId + " non trovato"));

            List<Orders> ordersList = orderRepository.findAllByUsers_Id (userId);

            return ordersMapper.toOrdersDTOList (ordersList);

        } catch (Exception e) {
            log.warn ("Eccezione nella richiesta  lista degli ordini dell utente: {}", e.getMessage ());
            return null;
        }

    }

    @Transactional
    public List<OrdersDTO> getAllByUserIdQuery (Long id) {
        List<Orders> ordersList = orderRepository.findAllByUserIdQuerySchiantata (id);
        System.out.println (ordersList);
        return ordersList.stream ().map (ordersMapper::toOrdersDTO).toList ();

    }

    @Transactional
    public Page<Orders> getAllOrders (Pageable pageable) {
        return orderRepository.findAll (pageable);
    }
}


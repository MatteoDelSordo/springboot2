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
        log.info ("Recupero di tutti gli ordini");
        List<Orders> o = orderRepository.findAll ();
        log.info ("Ordini recuperati");
        return ordersMapper.toOrdersDTOList (o);
    }

    //Get di un ordine specifico tramite id
    @Transactional
    public OrdersDTO findOrderById (Long id) {
        log.info ("RIcerca dell ordine con id: {}", id);
        Orders orderDaMappare = orderRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Ordine con id: " + id + " non trovato"));
        log.info ("Ordine trovato");
        return ordersMapper.toOrdersDTO (orderDaMappare);


    }

    //Aggiunge un ordine e crea un user
    @Transactional
    public void addOrderWithUser (OrdersDTO ordersDTO,
                                  UsersDTO usersDTO) {
        log.info ("Aggiunta di un ordine e di un utente");
        Users user = usersMapper.toEntity (usersDTO);
        Users userPerOrdine = userRepository.save (user);
        Orders order = ordersMapper.toOrders (ordersDTO);

        order.setUsers (userPerOrdine);
        orderRepository.save (order);
        log.info ("Ordine e utente aggiunti");
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
        log.info ("Modifica dell ordine con id: {}", id);
        Orders franco = orderRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Ordine con id: " + id + " non trovato"));

        log.debug ("Ordine prima di essere modificato; {}", franco);
        OrdersDTO orderDaModificare = ordersMapper.toOrdersDTO (franco);
        orderDaModificare.setProduct (ordersDTO.getProduct ());
        orderDaModificare.setAmount (ordersDTO.getAmount ());
        orderDaModificare.setProductType (ordersDTO.getProductType ());

        Orders orderModificato = ordersMapper.toOrders (orderDaModificare);

        log.debug ("Ordine dopo essere modificato. {}", orderModificato);
        log.info ("Ordine con id: {} modificato", id);
        orderRepository.save (orderModificato);


    }

    @Transactional
    public Orders addOrderToUser (Long idUser,
                                  OrdersDTO ordersDTO) {
        log.info ("Creo un ordine e lo aggiungo a un utente");

        Users user =
                userRepository.findById (idUser).orElseThrow (() -> new ResourceNotFoundException ("User con " + "id" + ":" + " " + idUser + " non trovato"));
        log.debug ("Utente con id: {} trovato{}", idUser, user);
        Orders order = ordersMapper.toOrders (ordersDTO);

        order.setUsers (user);
        log.debug ("Ordine: {} aggiunto a utente", order);
        log.info ("Ordine salvato");
        return orderRepository.save (order);
    }

    @Transactional
    public Orders addExistingOrderToUser (Long idUser,
                                          Long idOrder) {
        log.info ("Assegnazione di un ordine esistente a un utente");
        Users user =
                userRepository.findById (idUser).orElseThrow (() -> new ResourceNotFoundException ("User con " + "id" + ":" + " " + idUser + " non trovato"));
        log.debug ("Utente trovato: {}", user);
        Orders order = orderRepository.findById (idOrder).orElseThrow (() -> new ResourceNotFoundException (
                "Ordine non trovato"));
        log.debug ("Ordine trovato: {}", order);
        order.setUsers (user);
        log.info ("Ordine aggiornato");
        return orderRepository.save (order);

    }

    @Transactional
    public void deleteById (Long id) {
//        Optional<Orders> optionalOrders = orderRepository.findById(id);
//        Orders orders = optionalOrders.get();
        try {
            log.info ("Canellazione del ordine con id: {}", id);
            Orders orders = orderRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                    "L'ordine non è presente nel database degli ordini"));
            log.info ("L'ordine è stato eliminato");
            orderRepository.delete (orders);

        } catch (Exception exception) {
            System.out.println ("Qualcosa è andato storto nell eliminazione dell ordine con id: " + id);

        }
    }

    @Transactional
    public List<OrdersDTO> getOrdersOfUsersById (Long userId) {

        try {
            log.info ("Recupero degli ordini dell utente con id: {}", userId);
            Users users = userRepository.findById (userId).orElseThrow (() -> new ResourceNotFoundException (
                    "Utente con id: " + userId + " non trovato"));
            log.debug ("Utente {} trovato", users);
            List<Orders> ordersList = orderRepository.findAllByUsers_Id (userId);
            log.debug ("Lista di ordini relativa all utente recuperata {}", ordersList);
            log.info ("Lista recuperata");
            return ordersMapper.toOrdersDTOList (ordersList);

        } catch (Exception e) {
            log.warn ("Eccezione nella richiesta  lista degli ordini dell utente: {}", e.getMessage ());
            return null;
        }

    }

    @Transactional
    public List<OrdersDTO> getAllByUserIdQuery (Long id) {
        log.info ("Recupero la lista di ordine dell utente con id: {} tramite query", id);
        List<Orders> ordersList = orderRepository.findAllByUserIdQuerySchiantata (id);
        log.debug ("Lista recuperata {}",ordersList);
        log.info ("Lista recuperata");
        return ordersList.stream ().map (ordersMapper::toOrdersDTO).toList ();

    }

    @Transactional
    public Page<Orders> getAllOrders (Pageable pageable) {
        return orderRepository.findAll (pageable);
    }
}


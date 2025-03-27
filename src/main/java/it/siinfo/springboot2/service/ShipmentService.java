package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.ShipmentDTO;
import it.siinfo.springboot2.entity.Address;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Shipment;
import it.siinfo.springboot2.mapper.ShipmentMapper;
import it.siinfo.springboot2.repository.AddressRepository;
import it.siinfo.springboot2.repository.OrderRepository;
import it.siinfo.springboot2.repository.ShipmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShipmentService {

    private ShipmentRepository shipmentRepository;
    private ShipmentMapper shipmentMapper;
    private AddressRepository addressRepository;
    private OrderRepository ordersRepository;


    public ShipmentService(ShipmentRepository shipmentRepository,
                           ShipmentMapper shipmentMapper,
                           AddressRepository addressRepository,
                           OrderRepository ordersRepository) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentMapper = shipmentMapper;
        this.addressRepository = addressRepository;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void createShipment(Long idOrders,
                               Long idAddress,
                               ShipmentDTO shipmentDTO) {
        Orders order = ordersRepository.findById(idOrders).orElseThrow(() -> new EntityNotFoundException(
                "Entità non trovata"));
        Address address = addressRepository.findById(idAddress).orElseThrow(() -> new EntityNotFoundException(
                "Entità non trovata"));

        Shipment shipment = shipmentMapper.toShipment(shipmentDTO);
        order.setShipment(shipment);
        address.setShipment(shipment);
        shipment.setOrders(order);
        shipment.setAddress(address);
        shipmentRepository.save(shipment);
        addressRepository.save(address);
        ordersRepository.save(order);
    }

    @Transactional
    public ShipmentDTO getShipmentById(Long id) {
        Shipment shipment = shipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Shipment non trovato con ID: " + id));
        return shipmentMapper.toShipmentDto(shipment);
    }

    @Transactional
    public List<ShipmentDTO> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipmentMapper.toShipmentListDto(shipments);
    }

    @Transactional
    public void updateShipment(Long id,
                               ShipmentDTO shipmentDTO) {
        Shipment shipmentToUpdate = shipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Shipment non trovato con ID: " + id));

        shipmentToUpdate.setRackingNumber(shipmentDTO.getRackingNumber());
        shipmentToUpdate.setStatus(shipmentDTO.getStatus());
        shipmentToUpdate.setEstimatedDeliveryDate(shipmentDTO.getEstimatedDeliveryDate());

        shipmentRepository.save(shipmentToUpdate);

    }

    @Transactional
    public void updateShipmentStatus(Long id,
                                     ShipmentDTO shipmentDTO) {
        Shipment shipmentToUpdate = shipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Shipment con id: " + id + " non trovato"));

        shipmentToUpdate.setStatus(shipmentDTO.getStatus());

        shipmentRepository.save(shipmentToUpdate);

    }

    @Transactional
    public ShipmentDTO getShipmentByOrderId(Long id) {

        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("pippo"));

        return shipmentMapper.toShipmentDto(orders.getShipment());


    }

    @Transactional
    public void deleteShipment(Long id) {
        if (!shipmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Shipment non trovato con ID: " + id);
        }
        Shipment pippo = shipmentRepository.findById(id).
                orElseThrow(()
                -> new EntityNotFoundException("Leva le dita dal naso"));

        Orders orders = pippo.getOrders();
        orders.setShipment(null);
        ordersRepository.save(orders);

        Address address = pippo.getAddress();
        address.setShipment(null);
        addressRepository.save(address);

        ShipmentDTO shipmentDTO = shipmentMapper.toShipmentDto(pippo);
        shipmentDTO.setAddressId(null);
        shipmentDTO.setOrdersId(null);

        Shipment shipment = shipmentMapper.toShipment(shipmentDTO);
        shipmentRepository.deleteById(id);

    }
}

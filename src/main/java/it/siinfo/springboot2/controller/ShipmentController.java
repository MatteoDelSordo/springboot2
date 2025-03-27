package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.ShipmentDTO;
import it.siinfo.springboot2.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/spedizione")
public class ShipmentController {


    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {

        this.shipmentService = shipmentService;

    }

    @GetMapping(path = "/getall")
    public List<ShipmentDTO> getAllShipment() {

        return shipmentService.getAllShipments();

    }

    @GetMapping(path = "/getbyid/{id}")
    public ShipmentDTO getShipmentById(@PathVariable Long id) {

        return shipmentService.getShipmentById(id);

    }

    @PostMapping(path = "/create/{idOrder}/{idAddress}")
    public void createShipment(@PathVariable Long idOrder,
                               @PathVariable Long idAddress,
                               @RequestBody ShipmentDTO shipmentDTO) {

        shipmentService.createShipment(idOrder, idAddress, shipmentDTO);

    }

    @PutMapping(path = "/update/{id}")
    public void updateShipment(@PathVariable Long id,
                               @RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.updateShipment(id, shipmentDTO);
    }

    @PutMapping(path = "/updatestatus/{id}")
    public void updateShipmentStatus(@PathVariable Long id,
                                     @RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.updateShipmentStatus(id, shipmentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);

    }

    @GetMapping(path = "/getbyorderid/{id}")

    public ShipmentDTO getByOrderId(Long id) {

        return shipmentService.getShipmentByOrderId(id);
    }


}

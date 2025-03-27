package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.ShipmentDTO;
import it.siinfo.springboot2.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    Shipment toShipment(ShipmentDTO shipmentDTO);

    @Mapping(target = "ordersId", source = "orders.id")
    @Mapping(target = "addressId", source = "address.id")
    ShipmentDTO toShipmentDto(Shipment shipment);

    List<Shipment> toShipmentList(List<ShipmentDTO> shipmentDTOList);

    List<ShipmentDTO> toShipmentListDto(List<Shipment> shipmentList);

}

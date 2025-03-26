package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.ShipmentDTO;
import it.siinfo.springboot2.entity.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ShipmentMapper {

    Shipment toShipment(ShipmentDTO shipmentDTO);

    @Mapping(target = "orders", source = "orders.id")
    @Mapping(target = "address", source = "address.id")
    ShipmentDTO toShipmentDto(Shipment shipment);

    List<Shipment> toShipmentList(List<ShipmentDTO> shipmentDTOList);

    List<ShipmentDTO> toShipmentListDto(List<Shipment> shipmentList);

}

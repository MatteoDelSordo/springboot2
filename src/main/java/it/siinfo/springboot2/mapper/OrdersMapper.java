package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {


    Orders toOrders(OrdersDTO ordersDTO);
    @Mapping(target = "userId", source = "users.id")
    OrdersDTO toOrdersDTO (Orders orders);

    List<Orders> toOrderList (List<OrdersDTO> ordersDTOS);
    List<OrdersDTO> toOrdersDTOList (List<Orders> orders);





}

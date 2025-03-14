package it.siinfo.springboot2.dto;

import java.util.Objects;

public class UserANdORderDto {
    private UsersDTO usersDTO;
    private OrdersDTO ordersDTO;

    public UserANdORderDto(UsersDTO usersDTO,
                           OrdersDTO ordersDTO) {
        this.usersDTO = usersDTO;
        this.ordersDTO = ordersDTO;
    }

    public UsersDTO getUsersDTO() {
        return usersDTO;
    }

    public void setUsersDTO(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    public OrdersDTO getOrdersDTO() {
        return ordersDTO;
    }

    public void setOrdersDTO(OrdersDTO ordersDTO) {
        this.ordersDTO = ordersDTO;
    }
}

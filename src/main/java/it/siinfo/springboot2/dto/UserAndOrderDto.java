package it.siinfo.springboot2.dto;

public class UserAndOrderDto {
    private UsersDTO usersDTO;
    private OrdersDTO ordersDTO;

    public UserAndOrderDto() {
    }

    public UserAndOrderDto(UsersDTO usersDTO,
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

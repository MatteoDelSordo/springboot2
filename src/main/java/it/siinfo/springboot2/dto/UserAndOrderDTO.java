package it.siinfo.springboot2.dto;

public class UserAndOrderDTO {
    private UsersDTO usersDTO;
    private OrdersDTO ordersDTO;

    public UserAndOrderDTO() {
    }

    public UserAndOrderDTO(UsersDTO usersDTO,
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

package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {


    Users toUser(UsersDTO usersDTO);

    UsersDTO toUserDto(Users users);

    List<Users> toUsersList(List<UsersDTO> usersDTOS);

    List<UsersDTO> toUsersDtoList(List<Users> users);


}

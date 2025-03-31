package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {


    Users toEntity(UsersDTO usersDTO);

    UsersDTO toDto(Users users);

    List<Users> toUsersDTOList(List<UsersDTO> usersDTOS);

    List<UsersDTO> toUsersList(List<Users> users);


}

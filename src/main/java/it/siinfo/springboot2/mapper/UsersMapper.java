//package it.siinfo.springboot2.mapper;
//
//import it.siinfo.springboot2.dto.UsersDTO;
//import it.siinfo.springboot2.entity.Users;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface UsersMapper {
//    @Mapping(target = "orders", ignore = true)
//    Users toEntity(UsersDTO usersDTO);
//
//
//    @Mapping(target = "list", source = "orders")
//    UsersDTO toDto(Users users);
//}

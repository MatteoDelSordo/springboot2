package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.RoleDTO;
import it.siinfo.springboot2.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {


//    @Mapping (source = "idRole",target = "id")
    @Mapping (target = "users", ignore = true)
    Role toRole(RoleDTO roleDTO);


//    @Mapping (source = "id",target = "idRole")
    RoleDTO toRoleDto(Role role);



    Set<Role> toRoleSet(Set<RoleDTO> roleDTOS);


    Set<RoleDTO> toRoleDtoSet(Set<Role> roles);







}

package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Role;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsersMapper {

    private RoleRepository repository;
    private AddressMapper addressMapper;

    public UsersMapper (RoleRepository repository,
                        AddressMapper addressMapper) {
        this.repository = repository;
        this.addressMapper = addressMapper;
    }


    public UsersDTO toUserDto (Users user) {
        UsersDTO usersDTO = new UsersDTO ();
        usersDTO.setId (user.getId ());
        usersDTO.setName (user.getName ());
        usersDTO.seteMail (user.geteMail ());
        usersDTO.setPassword (user.getPassword ());
        usersDTO.setCreatedAt (user.getCreatedAt ());
        usersDTO.setPhoneNumber (user.getPhoneNumber ());
        usersDTO.setAddress (addressMapper.toAddressDto (user.getAddress ()));
        usersDTO.setIdRoles (user.getRoles ().stream ().map (Role::getId).collect (Collectors.toSet ()));
        return usersDTO;
    }

    public Users toUser (UsersDTO userDTO) {
        Users users = new Users ();
        users.setId (userDTO.getId ());
        users.setName (userDTO.getName ());
        users.seteMail (userDTO.geteMail ());
        users.setPassword (userDTO.getPassword ());
        users.setCreatedAt (userDTO.getCreatedAt ());
        users.setPhoneNumber (userDTO.getPhoneNumber ());
        users.setAddress (addressMapper.toAddress (userDTO.getAddress ()));
        Set<Role> roles = new HashSet<> (repository.findAllById (userDTO.getIdRoles ()));
        users.setRoles (roles);
        return users;
    }

    public List<UsersDTO> toUserDtoList (List<Users> users) {
        return users.stream ().map (this::toUserDto).collect (Collectors.toList ());
    }

    public List<Users> toUserList (List<UsersDTO> usersDTO) {
        return usersDTO.stream ().map (this::toUser).collect (Collectors.toList ());
    }

}

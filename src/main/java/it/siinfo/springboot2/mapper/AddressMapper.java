package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);
    @Mapping(target = "userId",source = "user.id")
    AddressDTO toAddressDto (Address address);



}

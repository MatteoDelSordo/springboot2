package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.AddressDTO;
import it.siinfo.springboot2.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "user", ignore = true)
//    @Mapping(target = "street", source = "street")
//    @Mapping(target = "city", source = "city")
//    @Mapping(target = "state", source = "state")
//    @Mapping(target = "zipCode", source = "zipCode")
//    @Mapping(target = "country", source = "country")
    Address toAddress(AddressDTO addressDTO);

    @Mapping(target = "userId", source = "user.id")
    AddressDTO toAddressDto(Address address);

    List<Address> toAddressList(List<AddressDTO> addressDTOS);

    List<AddressDTO> toAddress(List<Address> addresses);


}

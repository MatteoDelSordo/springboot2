package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.PaymentMethodDTO;
import it.siinfo.springboot2.entity.PaymentMethod;
import org.mapstruct.Mapping;

import java.util.List;

public interface PaymentMethodMapper {

    PaymentMethod toPaymentMethod(PaymentMethodDTO paymentMethodDTO);

    @Mapping(target = "userId", source = "user.id")
    PaymentMethodDTO toPaymentMethodDto(PaymentMethod paymentMethod);


    List<PaymentMethod> toPaymentMethodList(List<PaymentMethodDTO> paymentMethodDTO);

    List<PaymentMethodDTO> toPaymentMethodListDto(List<PaymentMethod> paymentMethod);


}

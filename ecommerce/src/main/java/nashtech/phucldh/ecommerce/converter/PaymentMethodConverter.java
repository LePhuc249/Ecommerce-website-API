package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.PaymentMethod.PaymentMethodDTO;
import nashtech.phucldh.ecommerce.entity.PaymentMethod;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethodDTO convertPaymentMethodToDTO(PaymentMethod payment) {
        try {
            PaymentMethodDTO dto = modelMapper.map(payment, PaymentMethodDTO.class);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert PaymentMethod to PaymentMethodDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public PaymentMethod convertPaymentMethodDTOToEntity(PaymentMethodDTO dto) {
        try {
            PaymentMethod entity = modelMapper.map(dto, PaymentMethod.class);
            return entity;
        } catch (Exception e) {
            LOGGER.info("Fail to convert PaymentMethodDTO to PaymentMethod");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }
}

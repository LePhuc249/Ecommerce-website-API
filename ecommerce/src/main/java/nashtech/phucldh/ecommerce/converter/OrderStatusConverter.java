package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.OrderStatus.OrderStatusDTO;
import nashtech.phucldh.ecommerce.entity.OrderStatus;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public OrderStatusDTO convertOrderStatusToDTO(OrderStatus status) {
        try {
            OrderStatusDTO dto = modelMapper.map(status, OrderStatusDTO.class);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert OrderStatus to OrderStatusDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public OrderStatus convertOrderStatusDTOToEntity(OrderStatusDTO dto) {
        try {
            OrderStatus entity = modelMapper.map(dto, OrderStatus.class);
            return entity;
        } catch (Exception e) {
            LOGGER.info("Fail to convert OrderStatusDTO to OrderStatus");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}

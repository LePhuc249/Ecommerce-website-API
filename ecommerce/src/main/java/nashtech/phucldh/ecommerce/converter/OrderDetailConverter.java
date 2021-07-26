package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.dto.OrderDetailDTO;

import nashtech.phucldh.ecommerce.entity.OrderDetail;

import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountOrderConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public OrderDetailDTO convertOrderDetailToDTO(OrderDetail orderDetail) {
        try {
            OrderDetailDTO dto = modelMapper.map(orderDetail, OrderDetailDTO.class);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Account to AccountDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public OrderDetail convertOrderDetailToEntity(OrderDetailDTO orderDetailDTO) {
        try {
            OrderDetail entity = modelMapper.map(orderDetailDTO, OrderDetail.class);
            return entity;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Account to AccountDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}

package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.AccountStatus.AccountStatusDTO;
import nashtech.phucldh.ecommerce.entity.AccountStatus;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountStatusConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public AccountStatusDTO convertAccountStatusToDTO(AccountStatus status) {
        try {
            AccountStatusDTO dto = modelMapper.map(status, AccountStatusDTO.class);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert AccountStatus to AccountStatusDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public AccountStatus convertAccountStatusDTOToEntity(AccountStatusDTO dto) {
        try {
            AccountStatus status = modelMapper.map(dto, AccountStatus.class);
            return status;
        } catch (Exception e) {
            LOGGER.info("Fail to convert AccountStatusDTO to AccountStatus");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}

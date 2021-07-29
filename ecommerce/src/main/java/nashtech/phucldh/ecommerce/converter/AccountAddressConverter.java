package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.AccountAddress.AccountAddressDTO;
import nashtech.phucldh.ecommerce.entity.AccountAddress;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountAddressConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public AccountAddressDTO convertAccountAddressToDTO(AccountAddress address) {
        try {
            AccountAddressDTO dto = modelMapper.map(address, AccountAddressDTO.class);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert AccountAddress to AccountAddressDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public AccountAddress convertAccountAddressDTOToEntity(AccountAddressDTO dto) {
        try {
            AccountAddress entity = modelMapper.map(dto, AccountAddress.class);
            return entity;
        } catch (Exception e) {
            LOGGER.info("Fail to convert AccountAddressDTO to AccountAddress");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}

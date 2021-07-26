package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.dto.AccountOrderDTO;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.AccountOrder;

import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;

import nashtech.phucldh.ecommerce.service.AccountService;
import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class AccountOrderConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountOrderConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountService accountService;

    public AccountOrderDTO convertAccountOrderToDTO(AccountOrder accountOrder) {
        try {
            AccountOrderDTO dto = modelMapper.map(accountOrder, AccountOrderDTO.class);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Account to AccountDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public AccountOrder convertAccountOrderToEntity(AccountOrderDTO dto) {
        try {
            AccountOrder order = modelMapper.map(dto, AccountOrder.class);
            Account account = accountService.getAccountDetail(order.getAccount().getId());
            order.setAccount(account);
            return order;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Account to AccountDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}

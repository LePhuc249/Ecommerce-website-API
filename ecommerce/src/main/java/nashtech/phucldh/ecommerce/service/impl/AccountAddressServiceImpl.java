package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.AccountAddressConverter;
import nashtech.phucldh.ecommerce.dto.AccountAddress.AccountAddressDTO;
import nashtech.phucldh.ecommerce.entity.AccountAddress;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.AccountAddressRepository;
import nashtech.phucldh.ecommerce.service.AccountAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountAddressServiceImpl implements AccountAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAddressServiceImpl.class);

    @Autowired
    AccountAddressRepository accountaddressRepository;

    @Autowired
    AccountAddressConverter accountAddressConverter;

    @Override
    public Boolean addNewAddress(AccountAddressDTO dto) throws CreateDataFailException {
        boolean result;
        try {
            AccountAddress checkAddress = accountaddressRepository.checkExistedAccountAddress(dto.getAccountId(),dto.getAddress());
            if (checkAddress != null) {
                LOGGER.info("The address for account: " + dto.getAccountId() + " address: " + dto.getAddress() + " have been existed");
                throw new DuplicateDataException(ErrorCode.ERR_ACCOUNT_ADDRESS_EXISTED);
            } else {
                AccountAddress address = accountAddressConverter.convertAccountAddressDTOToEntity(dto);
                accountaddressRepository.save(address);
                result = true;
            }
        } catch (Exception e) {
            LOGGER.info("Having error when add new address for account: " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateAddress(AccountAddressDTO dto) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            AccountAddress address = accountAddressConverter.convertAccountAddressDTOToEntity(dto);
            Optional<AccountAddress> addressOptional = accountaddressRepository.findById(address.getId());
            if (!addressOptional.isPresent())  {
                LOGGER.info("Can't find address for account: " + address.getAccount());
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
            }
            accountaddressRepository.save(address);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update address for account: " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteAddress(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        try {
            Optional<AccountAddress> addressOptional = accountaddressRepository.findById(id);
            if (!addressOptional.isPresent()) {
                LOGGER.info("Can't find address for account id: " + id);
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
            }
            accountaddressRepository.deleteById(id);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete address for account id: " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public List<AccountAddressDTO> getListAddress(Long id) throws DataNotFoundException {
        List<AccountAddressDTO> listDTO;
        try {
            List<AccountAddress> listAddress = accountaddressRepository.findAddressByAccountId(id);
            if(listAddress.size() > 0) {
                listDTO = new ArrayList<>();
                for(AccountAddress address : listAddress) {
                    AccountAddressDTO dto = accountAddressConverter.convertAccountAddressToDTO(address);
                    listDTO.add(dto);
                }
            } else {
                LOGGER.info("Can't find list address for account id: " + id);
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_EMPTY);
            }
        } catch (Exception e) {
            LOGGER.info("Having error when get address list by account id: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        return listDTO;
    }

}
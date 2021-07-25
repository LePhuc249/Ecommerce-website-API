package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.AccountAddress;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.repository.AccountAddressRepository;

import nashtech.phucldh.ecommerce.service.AccountAddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountAddressServiceImpl implements AccountAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountAddressServiceImpl.class);

    @Autowired
    AccountAddressRepository accountaddressRepository;

    @Override
    public Boolean addNewAddress(AccountAddress address) throws CreateDataFailException {
        boolean result = false;
        try {
            accountaddressRepository.save(address);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't add new address for account: %s ", address.getAccount());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateAddress(AccountAddress address) throws DataNotFoundException, UpdateDataFailException {
        boolean result = false;
        AccountAddress aAddress = null;
        Optional<AccountAddress> addressOptional = accountaddressRepository.findById(address.getId());
        if (addressOptional.isPresent()) {
            aAddress = addressOptional.get();
        } else {
            LOGGER.info("Can't find address for account: %s ", address.getAccount());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        try {
            accountaddressRepository.save(address);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't update address for account: %s ", address.getAccount());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteAddress(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result = false;
        AccountAddress address = null;
        Optional<AccountAddress> addressOptional = accountaddressRepository.findById(id);
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            LOGGER.info("Can't find address for account: %s ", address.getAccount());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        try {
            accountaddressRepository.save(address);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't delete address for account: %s ", address.getAccount());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public List<String> getListAddress(Long id) throws DataNotFoundException {
        List<String> listAddress = null;
        try {
            listAddress = accountaddressRepository.findAddressByAccountId(id);
        } catch (Exception ex) {
            LOGGER.info("Can't find address list size: %d ", listAddress.size());
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        return listAddress;
    }

}
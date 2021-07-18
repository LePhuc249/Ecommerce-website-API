package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.AccountAddress;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.AccountAddressRepository;
import nashtech.phucldh.ecommerce.service.AccountAddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AccountAddressServiceImpl implements AccountAddressService {

    @Autowired
    AccountAddressRepository accountaddressRepository;

    @Override
    public void addNewAddress(AccountAddress address) throws CreateDataFailException {
        try {
            accountaddressRepository.save(address);
        } catch (Exception e) {
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ADDRESS_FAIL);
        }
    }

    @Override
    public void updateAddress(AccountAddress address) throws DataNotFoundException, UpdateDataFailException {
        AccountAddress aAddress = null;
        Optional<AccountAddress> addressOptional = accountaddressRepository.findById(address.getId());
        if (addressOptional.isPresent()) {
            aAddress = addressOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        try {
            accountaddressRepository.save(address);
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ADDRESS_FAIL);
        }

    }

    @Override
    public void deleteAddress(Long id) throws DataNotFoundException, DeleteDataFailException {
        AccountAddress address = null;
        Optional<AccountAddress> addressOptional = accountaddressRepository.findById(id);
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        try {
            accountaddressRepository.save(address);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ACCOUNT_ADDRESS_FAIL);
        }
    }

    @Override
    public List<String> getListAddress(Long id) throws DataNotFoundException {
        List<String> listAddress = null;
        try {
            listAddress = accountaddressRepository.findAddressByAccountId(id);
        } catch (Exception ex) {
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ADDRESS_NOT_FOUND);
        }
        return listAddress;
    }
}

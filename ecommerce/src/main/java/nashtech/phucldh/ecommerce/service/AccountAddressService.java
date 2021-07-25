package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.AccountAddress;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface AccountAddressService {

    public List<String> getListAddress(Long id) throws DataNotFoundException;

    public Boolean addNewAddress(AccountAddress address) throws CreateDataFailException;

    public Boolean updateAddress(AccountAddress address) throws DataNotFoundException, UpdateDataFailException;

    public Boolean deleteAddress(Long id) throws DataNotFoundException, DeleteDataFailException;

}
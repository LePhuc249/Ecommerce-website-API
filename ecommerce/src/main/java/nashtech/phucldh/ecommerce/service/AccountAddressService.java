package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.AccountAddress;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface AccountAddressService {

    public void addNewAddress(AccountAddress address) throws CreateDataFailException;

    public void updateAddress(AccountAddress address) throws DataNotFoundException, UpdateDataFailException;

    public void deleteAddress(Long id) throws DataNotFoundException, DeleteDataFailException;

    public List<String> getListAddress(Long id) throws DataNotFoundException;

}
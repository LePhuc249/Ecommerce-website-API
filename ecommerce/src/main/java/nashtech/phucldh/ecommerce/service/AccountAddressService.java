package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.AccountAddress.AccountAddressDTO;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface AccountAddressService {

    List<AccountAddressDTO> getListAddress(Long id) throws DataNotFoundException;

    Boolean addNewAddress(AccountAddressDTO dto) throws CreateDataFailException;

    Boolean updateAddress(AccountAddressDTO dto) throws DataNotFoundException, UpdateDataFailException;

    Boolean deleteAddress(Long id) throws DataNotFoundException, DeleteDataFailException;

}
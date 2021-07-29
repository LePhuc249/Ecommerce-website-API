package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.OrganizationAddress.OrganizationAddressDTO;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface OrganizationAddressService {

    List<OrganizationAddress> findAllAddress() throws DataNotFoundException;

    List<String> getListStringAddress(Long id) throws DataNotFoundException;

    OrganizationAddress findAddressById(Long id) throws DataNotFoundException;

    OrganizationAddress getOrganizationAddress(Long id, String address) throws DataNotFoundException;

    Boolean createNewAddress(OrganizationAddressDTO organizationAddressDTO) throws CreateDataFailException;

    Boolean updateAddress(OrganizationAddressDTO organizationAddressDTO) throws UpdateDataFailException;

    Boolean deleteAddress(Long organizationAddressID) throws DeleteDataFailException;

    List<OrganizationAddressDTO> getOrganizationAddressToShow(Long id) throws DataNotFoundException;

    List<OrganizationAddressDTO> getListAllOrganizationAddressToShow(int pageNo, String valueSort);

}
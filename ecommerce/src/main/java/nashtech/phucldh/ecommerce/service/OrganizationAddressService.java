package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.OrganizationAddressDTO;

import nashtech.phucldh.ecommerce.entity.OrganizationAddress;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganizationAddressService {

    public List<OrganizationAddress> findAllAddress() throws DataNotFoundException;

    public List<OrganizationAddress> getListAddressOfOrganization(Long id) throws DataNotFoundException;

    public List<String> getListStringAddress(Long id) throws DataNotFoundException;

    public OrganizationAddress findAddressById(Long id) throws DataNotFoundException;

    public OrganizationAddress getOrganizationAddress(Long id, String address) throws DataNotFoundException;

    public Boolean createNewAddress(OrganizationAddressDTO organizationAddressDTO) throws CreateDataFailException;

    public Boolean updateAddress(OrganizationAddressDTO organizationAddressDTO) throws UpdateDataFailException;

    public Boolean deleteAddress(Long organizationAddressID) throws DeleteDataFailException;

    public Page<OrganizationAddress> getPaginationOrganizationAddress(int pageNo, String valueSort);

}
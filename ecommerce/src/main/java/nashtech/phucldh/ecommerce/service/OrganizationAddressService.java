package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface OrganizationAddressService {

    public List<OrganizationAddress> findAllAddress() throws DataNotFoundException;

    public OrganizationAddress findAddressById(Long id) throws DataNotFoundException;

    public List<OrganizationAddress> getListAddressOfOrganization(Long id) throws DataNotFoundException;

    public List<String> getListStringAddress(Long id) throws DataNotFoundException;

    public OrganizationAddress getOrganizationAddress(Long id, String address) throws DataNotFoundException;

    public void createNewAddress(OrganizationAddress organizationAddress) throws CreateDataFailException;

    public void updateAddress(OrganizationAddress organization) throws UpdateDataFailException;

    public void deleteAddress(Long organizationAddressID) throws DeleteDataFailException;

}
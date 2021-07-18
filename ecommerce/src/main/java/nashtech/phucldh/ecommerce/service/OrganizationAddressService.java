package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import java.util.List;

public interface OrganizationAddressService {

    public List<OrganizationAddress>  findAllAddress() throws DataNotFoundException;

    public OrganizationAddress findAddressById(Long id) throws DataNotFoundException;

    public List<OrganizationAddress> getListAddressOfOrganization(Long id) throws DataNotFoundException;

    public List<String> getListStringAddress(Long id) throws DataNotFoundException;
}

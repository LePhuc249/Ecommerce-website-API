package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrganizationAddressRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationAddressRepository organizationAddressRepository;

    @Test
    public void getAllAdress() {
        List<OrganizationAddress> list = organizationAddressRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getAddress() throws DataNotFoundException {
        OrganizationAddress address;
        Optional<OrganizationAddress> result = organizationAddressRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            address = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        Assert.notNull(address);
    }

    @Test
    public void addAddress() throws DataNotFoundException {
        Organization organization;
        Optional<Organization> result = organizationRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            organization = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        OrganizationAddress address = new OrganizationAddress();
        address.setAddress("Ha Noi");
        address.setOrganization(organization);
        Assert.notNull(organizationAddressRepository.save(address));
    }

    @Test
    public void updateAddress() throws DataNotFoundException {
        OrganizationAddress address;
        Optional<OrganizationAddress> result = organizationAddressRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            address = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        address.setAddress("Sapa");
        Assert.notNull(organizationAddressRepository.save(address));
    }

    @Test
    public void deleteAddress() throws DataNotFoundException {
        OrganizationAddress address;
        Optional<OrganizationAddress> result = organizationAddressRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            address = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        organizationAddressRepository.deleteById(address.getId());
        Optional<OrganizationAddress> resultAfterDelete = organizationAddressRepository.findById(Long.valueOf("1"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

}

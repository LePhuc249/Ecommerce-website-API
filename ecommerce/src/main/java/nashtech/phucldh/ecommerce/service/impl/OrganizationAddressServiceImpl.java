package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.OrganizationAddressRepository;
import nashtech.phucldh.ecommerce.service.OrganizationAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationAddressServiceImpl implements OrganizationAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationAddressServiceImpl.class);

    @Autowired
    OrganizationAddressRepository organizationAddressRepository;

    @Override
    public List<OrganizationAddress> findAllAddress() throws DataNotFoundException {
        List<OrganizationAddress> listAllOrganizationAddress = null;
        try {
            listAllOrganizationAddress = organizationAddressRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Can't find organization address ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return listAllOrganizationAddress;
    }

    @Override
    public OrganizationAddress findAddressById(Long id) throws DataNotFoundException {
        OrganizationAddress organizationAddress = null;
        Optional<OrganizationAddress> imageOptional = organizationAddressRepository.findById(id);
        if (imageOptional.isPresent()) {
            organizationAddress = imageOptional.get();
        } else {
            LOGGER.info("Can't find organization address ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return organizationAddress;
    }

    @Override
    public List<OrganizationAddress> getListAddressOfOrganization(Long id) throws DataNotFoundException {
        List<OrganizationAddress> listOrganizationAddress = null;
        try {
            listOrganizationAddress = organizationAddressRepository.getAddressByOrganization(id);
        } catch (Exception e) {
            LOGGER.info("Can't find organization address ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return listOrganizationAddress;
    }

    @Override
    public List<String> getListStringAddress(Long id) throws DataNotFoundException {
        List<String> listAddress = null;
        try {
            listAddress = organizationAddressRepository.getListAddressOfOrganization(id);
        } catch (Exception e) {
            LOGGER.info("Can't find organization address ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return listAddress;
    }

}
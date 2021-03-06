package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.OrganizationAddressConverter;
import nashtech.phucldh.ecommerce.dto.OrganizationAddress.OrganizationAddressDTO;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.repository.OrganizationAddressRepository;
import nashtech.phucldh.ecommerce.service.OrganizationAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationAddressServiceImpl implements OrganizationAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationAddressServiceImpl.class);

    @Autowired
    OrganizationAddressRepository organizationAddressRepository;

    @Autowired
    OrganizationAddressConverter organizationAddressConverter;

    @Override
    public List<OrganizationAddress> findAllAddress() throws DataNotFoundException {
        List<OrganizationAddress> listAllOrganizationAddress;
        try {
            listAllOrganizationAddress = organizationAddressRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Having error when find organization address " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return listAllOrganizationAddress;
    }

    @Override
    public OrganizationAddress findAddressById(Long id) throws DataNotFoundException {
        OrganizationAddress organizationAddress = organizationAddressRepository.getOrganizationAddressById(id);
        if (organizationAddress == null) {
            LOGGER.info("Can't find organization address ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return organizationAddress;
    }

    @Override
    public List<String> getListStringAddress(Long id) throws DataNotFoundException {
        List<String> listAddress;
        try {
            listAddress = organizationAddressRepository.getListAddressOfOrganization(id);
        } catch (Exception e) {
            LOGGER.info("Having error when find organization address " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return listAddress;
    }

    @Override
    public OrganizationAddress getOrganizationAddress(Long id, String address) throws DataNotFoundException {
        OrganizationAddress organizationAddress = null;
        if (id != null && address != null) {
            organizationAddress = organizationAddressRepository.getOrganizationAddress(id, address);
        }
        return organizationAddress;
    }

    @Override
    public Boolean createNewAddress(OrganizationAddressDTO organizationAddressDTO) throws CreateDataFailException {
        boolean result;
        try {
            OrganizationAddress organizationAddress = organizationAddressConverter.convertOrganizationDTOToEntity(organizationAddressDTO);
            OrganizationAddress temp = getOrganizationAddress(organizationAddress.getId(), organizationAddress.getAddress());
            if (temp != null) {
                LOGGER.info("Organization address have been existed");
                throw new DuplicateDataException(ErrorCode.ERR_ORGANIZATION_ADDRESS_EXISTED);
            }
            organizationAddressRepository.save(organizationAddress);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when create organization address " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateAddress(OrganizationAddressDTO organizationAddressDTO) throws UpdateDataFailException {
        boolean result;
        try {
            OrganizationAddress organizationAddress = organizationAddressConverter.convertOrganizationDTOToEntity(organizationAddressDTO);
            OrganizationAddress temp = findAddressById(organizationAddress.getId());
            if (temp == null) {
                LOGGER.info("Can't find the organization address");
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            organizationAddressRepository.save(organizationAddress);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when Update organization address " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteAddress(Long organizationAddressID) throws DeleteDataFailException {
        boolean result;
        try {
            Optional<OrganizationAddress> optionalOrganizationAddress = organizationAddressRepository.findById(organizationAddressID);
            if (!optionalOrganizationAddress.isPresent()) {
                LOGGER.info("Can't find the organization address");
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            organizationAddressRepository.deleteById(organizationAddressID);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete organization address " + e.getMessage());
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
        }
        return result;
    }

    @Override
    public List<OrganizationAddressDTO> getOrganizationAddressToShow(Long id) throws DataNotFoundException {
        List<OrganizationAddressDTO> listDTO;
        try {
            List<OrganizationAddress> listOrganizationAddress = organizationAddressRepository.getAddressByOrganization(id);
            if (listOrganizationAddress.size() > 0) {
                listDTO = new ArrayList<>();
                for (OrganizationAddress address : listOrganizationAddress) {
                    OrganizationAddressDTO dto = organizationAddressConverter.convertOrganizationToDTO(address);
                    listDTO.add(dto);
                }
            } else {
                LOGGER.info("Can't find the address organization id: " + id);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.info("Having error when load the list address of organization: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<OrganizationAddressDTO> getListAllOrganizationAddressToShow(int pageNo, String valueSort) {
        List<OrganizationAddressDTO> listDTO;
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
            Page<OrganizationAddress> page = organizationAddressRepository.findAll(pageable);
            listDTO = organizationAddressConverter.toDTOList(page.getContent());
        } catch (Exception e) {
            LOGGER.info("Having error when load the list address of organization: " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

}
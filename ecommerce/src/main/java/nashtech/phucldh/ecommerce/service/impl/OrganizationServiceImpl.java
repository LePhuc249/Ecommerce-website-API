package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.converter.OrganizationConverter;

import nashtech.phucldh.ecommerce.dto.OrganizationDTO;
import nashtech.phucldh.ecommerce.entity.Organization;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.repository.OrganizationRepository;

import nashtech.phucldh.ecommerce.service.OrganizationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    OrganizationConverter organizationConverter;

    @Override
    public List<Organization> getAllOrganizations() throws DataNotFoundException {
        List<Organization> listAllOrganization;
        try {
            listAllOrganization = organizationRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Can't find all organization ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return listAllOrganization;
    }

    @Override
    public Organization getOrganizationByID(Long id) throws DataNotFoundException {
        Organization organization = organizationRepository.getOrganizationByOrganizationId(id);
        if (organization == null) {
            LOGGER.info("Can't find organization with organization id: " + id);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return organization;
    }

    @Override
    public Organization getOrganizationByName(String name) throws DataNotFoundException {
        Organization organization;
        Optional<Organization> imageOptional = organizationRepository.findByName(name);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            LOGGER.info("Can't find organization " + name);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return organization;
    }

    @Override
    public Organization checkExistedOrganization(String name, Long imageId) {
        Organization organization;
        if (name != null && imageId != null) {
            organization = organizationRepository.checkExistedOrganization(name, imageId);
        } else {
            LOGGER.info("Must input both of name and image");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return organization;
    }

    @Override
    public Boolean createOrganization(OrganizationDTO organizationDTO) throws CreateDataFailException {
        boolean result;
        try {
            Organization organization = organizationConverter.convertOrganizationDTOToEntity(organizationDTO);
            Organization tempOrganization = checkExistedOrganization(organization.getName(), organization.getImageOrganization().getId());
            if (tempOrganization != null) {
                LOGGER.info("The organization have been existed ");
                throw new DuplicateDataException(ErrorCode.ERR_ORGANIZATION_EXISTED);
            }
            organization.setCreateDate(LocalDateTime.now());
            organization.setDeleted(false);
            organizationRepository.save(organization);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't create organization ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateOrganization(OrganizationDTO organizationDTO) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            Organization organization = organizationConverter.convertOrganizationDTOToEntity(organizationDTO);
            Organization tempOrganization = getOrganizationByID(organization.getId());
            if (tempOrganization == null) {
                LOGGER.info("Can't find organization ");
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            organization.setCreateDate(organizationDTO.getCreateDate());
            organization.setUpdateDate(LocalDateTime.now());
            organizationRepository.save(organization);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update organization ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteOrganization(Long id) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Organization organization = null;
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isPresent()) {
            organization = organizationOptional.get();
        } else {
            LOGGER.info("Can't find organization ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        try {
            organizationRepository.deleteOrganization(id);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't delete organization ");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
        }
        return result;
    }

    @Override
    public Boolean activeOrganization(Long id) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Organization organization = null;
        Optional<Organization> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isPresent()) {
            organization = organizationOptional.get();
        } else {
            LOGGER.info("Can't find organization ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        try {
            organizationRepository.unDeleteOrganization(id);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update organization ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return result;
    }

    @Override
    public Long getStatusOfOrganization(Long id) throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> imageOptional = organizationRepository.findById(id);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            LOGGER.info("Can't find organization ");
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        Long status = organizationRepository.getStatusOfOrganization(id);
        return status;
    }

    @Override
    public Page<Organization> getPaginationOrganization(int pageNo, String valueSort) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
        Page<Organization> page = organizationRepository.findAll(pageable);
        return page;
    }

}
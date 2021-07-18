package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.OrganizationRepository;
import nashtech.phucldh.ecommerce.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public List<Organization> getAllOrganizations() throws DataNotFoundException {
        List<Organization> listAllOrganization = null;
        try {
            listAllOrganization = organizationRepository.findAll();
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return listAllOrganization;
    }

    @Override
    public Organization getOrganizationByID(Long id) throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> imageOptional = organizationRepository.findById(id);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return organization;
    }

    @Override
    public Organization getOrganizationByName(String name) throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> imageOptional = organizationRepository.findByName(name);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return organization;
    }

    @Override
    public void createOrganization(Organization organization) throws CreateDataFailException {
        try {
            organizationRepository.save(organization);
        } catch (Exception ex ) {
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
        }
    }

    @Override
    public void updateOrganization(Organization organization) throws DataNotFoundException, UpdateDataFailException {
        Organization tempOrganization = null;
        Optional<Organization> imageOptional = organizationRepository.findById(organization.getId());
        if (imageOptional.isPresent()) {
            tempOrganization = imageOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        try {
            organizationRepository.save(organization);
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
    }

    @Override
    public void deleteOrganization(Long id) throws DataNotFoundException, DeleteDataFailException {
        Organization organization = null;
        Optional<Organization> imageOptional = organizationRepository.findById(id);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        try {
            organizationRepository.deleteOrganization(id);
        } catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
        }
    }

    @Override
    public void activeOrganization(Long id) throws DataNotFoundException, UpdateDataFailException {
        Organization organization = null;
        Optional<Organization> imageOptional = organizationRepository.findById(id);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        try {
            organizationRepository.unDeleteOrganization(id);
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
    }

    @Override
    public Long getStatusOfOrganization(Long id) throws DataNotFoundException {
        Organization organization = null;
        Optional<Organization> imageOptional = organizationRepository.findById(id);
        if (imageOptional.isPresent()) {
            organization = imageOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        Long status = organizationRepository.getStatusOfOrganization(id);
        return status;
    }
}

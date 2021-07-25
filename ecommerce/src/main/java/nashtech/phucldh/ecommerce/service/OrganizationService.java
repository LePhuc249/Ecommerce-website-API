package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.OrganizationDTO;
import nashtech.phucldh.ecommerce.entity.Organization;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import org.springframework.data.domain.Page;

import java.util.List;

public interface OrganizationService {

    public List<Organization> getAllOrganizations() throws DataNotFoundException;

    public Organization getOrganizationByID(Long id) throws DataNotFoundException;

    public Organization getOrganizationByName(String name) throws DataNotFoundException;

    public Organization checkExistedOrganization(String name, Long imageId);

    public Boolean createOrganization(OrganizationDTO dtoOrganization) throws CreateDataFailException;

    public Boolean updateOrganization(OrganizationDTO dtoOrganization) throws DataNotFoundException, UpdateDataFailException;

    public Boolean deleteOrganization(Long id) throws DataNotFoundException, DeleteDataFailException;

    public Boolean activeOrganization(Long id) throws DataNotFoundException, UpdateDataFailException;

    public Long getStatusOfOrganization(Long id) throws DataNotFoundException;

    public Page<Organization> getPaginationOrganization(int pageNo, String valueSort);

}
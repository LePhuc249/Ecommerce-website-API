package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface OrganizationService {

    public List<Organization> getAllOrganizations() throws DataNotFoundException;

    public Organization getOrganizationByID(Long id) throws DataNotFoundException;

    public Organization getOrganizationByName(String name) throws DataNotFoundException;

    public void createOrganization(Organization organization) throws CreateDataFailException;

    public void updateOrganization(Organization organization) throws DataNotFoundException, UpdateDataFailException;

    public void deleteOrganization(Long id) throws DataNotFoundException, DeleteDataFailException;

    public void activeOrganization(Long id) throws DataNotFoundException, UpdateDataFailException;

    public Long getStatusOfOrganization(Long id) throws DataNotFoundException;

}
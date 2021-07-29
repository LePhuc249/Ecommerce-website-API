package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.dto.Organization.OrganizationDTO;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface OrganizationService {

    List<Organization> getAllOrganizations() throws DataNotFoundException;

    Organization getOrganizationByID(Long id) throws DataNotFoundException;

    Organization getOrganizationByName(String name) throws DataNotFoundException;

    Organization checkExistedOrganization(String name, Long imageId);

    Boolean createOrganization(OrganizationDTO dtoOrganization) throws CreateDataFailException;

    Boolean updateOrganization(OrganizationDTO dtoOrganization) throws DataNotFoundException, UpdateDataFailException;

    Boolean deleteOrganization(Long id) throws DataNotFoundException, DeleteDataFailException;

    Boolean activeOrganization(Long id) throws DataNotFoundException, UpdateDataFailException;

    Long getStatusOfOrganization(Long id) throws DataNotFoundException;

    OrganizationDTO getOrganizationToShowByID(Long id) throws DataNotFoundException;

    List<OrganizationDTO> getOrganizationListToShowByID(int pageNo, String valueSort) throws DataNotFoundException;

}
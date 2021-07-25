package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Role;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface RoleService {

    public Role getRoleById(Long idRole) throws DataNotFoundException;

}
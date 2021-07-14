package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface RoleService {

	public Role getRoleById(Integer idRole) throws DataNotFoundException;
}

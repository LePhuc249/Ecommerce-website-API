package nashtech.phucldh.ecommerce.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.exception.RoleNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.RoleRepository;
import nashtech.phucldh.ecommerce.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role getRoleById(String idRole) {
		Optional<Role> result = roleRepository.findById(idRole);
		Role theRole = null;
		if (result.isPresent()) {
			theRole = result.get();
		} else {
			throw new RoleNotFoundException("Did not find role by id - " + idRole);
		}
		return theRole;
	}

}

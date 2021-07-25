package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Role;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import nashtech.phucldh.ecommerce.repository.RoleRepository;

import nashtech.phucldh.ecommerce.service.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRoleById(Long idRole) throws DataNotFoundException {
        Optional<Role> result = roleRepository.findById(idRole);
        Role theRole;
        if (result.isPresent()) {
            theRole = result.get();
        } else {
            LOGGER.info("Can't find role ");
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        return theRole;
    }

}
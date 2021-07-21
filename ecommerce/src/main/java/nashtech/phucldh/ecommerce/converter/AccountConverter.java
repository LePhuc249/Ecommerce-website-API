package nashtech.phucldh.ecommerce.converter;

import java.util.Set;
import java.util.stream.Collectors;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.AccountProfileDTO;
import nashtech.phucldh.ecommerce.dto.RoleDTO;
import nashtech.phucldh.ecommerce.entity.ERole;
import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nashtech.phucldh.ecommerce.dto.AccountDTO;
import nashtech.phucldh.ecommerce.entity.Account;

@Component
public class AccountConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public AccountDTO convertAccountToDto(Account account) throws ConvertEntityDTOException {
        try {
            AccountDTO dto = modelMapper.map(account, AccountDTO.class);
            Set<String> roles = account.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toSet());
            dto.setRoles(roles);
            return dto;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Account to AccountDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public AccountProfileDTO convertAccountProfileToDto(Account account) throws ConvertEntityDTOException {
        try {
            AccountProfileDTO dto = modelMapper.map(account, AccountProfileDTO.class);
            Set<RoleDTO> roles = account.getRoles().stream()
                    .map(role -> {
                        if (role.getName().equals(ERole.Admin)) {
                            return new RoleDTO(role.getId(), "Admin");
                        } else if (role.getName().equals(ERole.Customer)) {
                            return new RoleDTO(role.getId(), "Customer");
                        } else {
                            return new RoleDTO(role.getId(), "Manager");
                        }
                    })
                    .collect(Collectors.toSet());
            dto.setRoles(roles);
            return dto;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Account to AccountProfileDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Role convertRoleToEntity(RoleDTO dto) throws ConvertEntityDTOException {
        try {
            Role role = new Role();
            role.setId(dto.getId());
            if (dto.getName().equals("Admin")) {
                role.setName(ERole.Admin);
            } else if (dto.getName().equals("Customer")) {
                role.setName(ERole.Customer);
            } else {
                role.setName(ERole.Manager);
            }
            return role;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert RoleDTO to Role");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Account convertAccountProfileToEntity(AccountProfileDTO dto) throws ConvertEntityDTOException {
        try {
            Account user = modelMapper.map(dto, Account.class);
            Set<Role> roles = dto.getRoles().stream().map(this::convertRoleToEntity).collect(Collectors.toSet());
            user.setRoles(roles);
            return user;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert UserProfileDTO to User");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public RoleDTO convertRoleToDTO(Role role) throws ConvertEntityDTOException {
        try {
            RoleDTO dto = new RoleDTO();
            dto.setId(role.getId());
            if (role.getName().equals(ERole.Admin)) {
                dto.setName("Admin");
            } else if (role.getName().equals(ERole.Customer)) {
                dto.setName("Customer");
            } else {
                dto.setName("Manager");
            }
            return dto;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Role to RoleDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }
}
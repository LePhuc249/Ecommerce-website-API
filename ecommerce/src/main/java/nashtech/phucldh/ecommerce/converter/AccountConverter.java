package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.Account.AccountProfileDTO;
import nashtech.phucldh.ecommerce.dto.Role.RoleDTO;
import nashtech.phucldh.ecommerce.dto.Account.AccountDTO;
import nashtech.phucldh.ecommerce.entity.ERole;
import nashtech.phucldh.ecommerce.entity.Role;
import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                        if (role.getName().equals(ERole.ROLE_ADMIN)) {
                            return new RoleDTO(role.getId(), "Admin");
                        } else if (role.getName().equals(ERole.ROLE_CUSTOMER)) {
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
                role.setName(ERole.ROLE_ADMIN);
            } else if (dto.getName().equals("Customer")) {
                role.setName(ERole.ROLE_CUSTOMER);
            } else {
                role.setName(ERole.ROLE_MANAGER);
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
            if (role.getName().equals(ERole.ROLE_ADMIN)) {
                dto.setName("Admin");
            } else if (role.getName().equals(ERole.ROLE_CUSTOMER)) {
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


    public AccountProfileDTO toDTO(Account entity) {
        AccountProfileDTO dto = new AccountProfileDTO();
        dto.setId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        Set<Role> setEntityRole = entity.getRoles();
        Set<RoleDTO> setDTORole = new HashSet<>();
        for (Role role : setEntityRole) {
            RoleDTO dtoRole = convertRoleToDTO(role);
            setDTORole.add(dtoRole);
        }
        dto.setRoles(setDTORole);
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<AccountProfileDTO> toDTOList(List<Account> listEntity) {
        List<AccountProfileDTO> listDTO = listEntity.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return listDTO;
    }

}
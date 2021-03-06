package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.OrganizationAddress.OrganizationAddressDTO;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.repository.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrganizationAddressConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    public OrganizationAddressDTO convertOrganizationToDTO(OrganizationAddress organizationAddress) {
        try {
            OrganizationAddressDTO dto = modelMapper.map(organizationAddress, OrganizationAddressDTO.class);
            return dto;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Brand to BrandDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public OrganizationAddress convertOrganizationDTOToEntity(OrganizationAddressDTO dtoOrganizationAddress) {
        try {
            OrganizationAddress organizationAddress = modelMapper.map(dtoOrganizationAddress, OrganizationAddress.class);
            Organization organization = null;
            Optional<Organization> optionalOrganization = organizationRepository.findById(dtoOrganizationAddress.getOrganizationId());
            if (optionalOrganization.isPresent()) {
                organization = optionalOrganization.get();
            } else {
                LOGGER.info("Can't find the organization address");
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            organizationAddress.setOrganization(organization);
            return organizationAddress;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Brand to BrandDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public OrganizationAddressDTO toDTO(OrganizationAddress entity) {
        OrganizationAddressDTO dto = new OrganizationAddressDTO();
        dto.setId(entity.getId());
        dto.setOrganizationId(entity.getOrganization().getId());
        dto.setAddress(entity.getAddress());
        return dto;
    }

    public List<OrganizationAddressDTO> toDTOList(List<OrganizationAddress> entityList) {
        List<OrganizationAddressDTO> dtoList = entityList.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return dtoList;
    }

}
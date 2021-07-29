package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.Organization.OrganizationDTO;
import nashtech.phucldh.ecommerce.entity.Image;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrganizationConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ImageRepository imageRepository;

    public OrganizationDTO convertOrganizationToDTO(Organization organization) {
        try {
            OrganizationDTO dto = modelMapper.map(organization, OrganizationDTO.class);
            return dto;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Organization to OrganizationDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Organization convertOrganizationDTOToEntity(OrganizationDTO dtoOrganization) {
        try {
            Organization organization = modelMapper.map(dtoOrganization, Organization.class);
            Image image = null;
            if (dtoOrganization.getImageOrganizationId() != null) {
                Optional<Image> optionalImage = imageRepository.findById(dtoOrganization.getImageOrganizationId());
                if (optionalImage.isPresent()) {
                    image = optionalImage.get();
                } else {
                    LOGGER.info("Can't find image");
                    throw new DataNotFoundException(ErrorCode.ERR_IMAGE_NOT_FOUND);
                }
                organization.setImageOrganization(image);
            }
            return organization;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Organization to OrganizationDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public OrganizationDTO toDTO(Organization entity) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImageOrganizationId(entity.getImageOrganization().getId());
        dto.setCreateBy(entity.getCreateBy());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setCreateBy(entity.getCreateBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public List<OrganizationDTO> toDTOList(List<Organization> entityList) {
        List<OrganizationDTO> dtoList = entityList.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return dtoList;
    }

}
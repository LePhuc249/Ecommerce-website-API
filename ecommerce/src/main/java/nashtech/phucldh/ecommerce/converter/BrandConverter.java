package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.BrandDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.OrganizationRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BrandConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    public BrandDTO convertBrandToDTO(Brand brand) {
        try {
            BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
            return brandDTO;
        } catch (Exception e) {
            LOGGER.info("Fail to convert Brand to BrandDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Brand convertBrandDTOToEntity(BrandDTO dtoBrand) {
        try {
            Brand brand = modelMapper.map(dtoBrand, Brand.class);
            Organization organization = null;
            Optional<Organization> optionalOrganization = organizationRepository.findById(dtoBrand.getOrganization());
            if (optionalOrganization.isPresent()) {
                organization = optionalOrganization.get();
            } else {
                LOGGER.info("Can't find the organization");
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            brand.setOrganization(organization);
            return brand;
        } catch (Exception e) {
            LOGGER.info("Fail to convert BrandDTO to Brand");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }
}
package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.CategoryDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.BrandRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;

    public Category convertCategoryToEntity(CategoryDTO categoryDTO) throws ConvertEntityDTOException {
        try {
            Category category = modelMapper.map(categoryDTO, Category.class);
            Brand brand = null;
            Optional<Brand> optionalBrand = brandRepository.findById(categoryDTO.getBrandId());
            if (optionalBrand.isPresent()) {
                brand = optionalBrand.get();
            } else {
                LOGGER.info("Can't find the brand");
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            category.setBrand(brand);
            return category;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert CategoryDTO to Category");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public CategoryDTO convertCategoryToDto(Category category) throws ConvertEntityDTOException {
        try {
            CategoryDTO dto = modelMapper.map(category, CategoryDTO.class);
            return dto;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Category to CategoryDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }
}
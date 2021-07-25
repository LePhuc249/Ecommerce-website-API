package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.dto.CategoryDTO;

import nashtech.phucldh.ecommerce.entity.Category;

import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;

import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public Category convertCategoryToEntity(CategoryDTO categoryDTO) throws ConvertEntityDTOException {
        try {
            Category category = modelMapper.map(categoryDTO, Category.class);
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

    public CategoryDTO toDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreateBy(entity.getCreateBy());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setCreateBy(entity.getCreateBy());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }

    public List<CategoryDTO> toDTOList(List<Category> entityList) {
        List<CategoryDTO> dtoList = entityList.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return dtoList;
    }

}
package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.CategoryConverter;
import nashtech.phucldh.ecommerce.dto.Category.CategoryDTO;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.repository.CategoryRepository;
import nashtech.phucldh.ecommerce.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryConverter categoryConverter;

    @Override
    public List<Category> findAll() throws DataNotFoundException {
        List<Category> theListCategory;
        try {
            theListCategory = categoryRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Having error when  find category " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return theListCategory;
    }

    @Override
    public Boolean save(CategoryDTO categoryDTO) throws CreateDataFailException {
        boolean result;
        try {
            Category category = categoryConverter.convertCategoryToEntity(categoryDTO);
            Category tempCategory = getCategoryByNameAndBrand(category.getName());
            if (tempCategory != null) {
                LOGGER.info("Category have been existed ");
                throw new DuplicateDataException(ErrorCode.ERR_CATEGORY_EXISTED);
            }
            category.setCreateDate(LocalDateTime.now());
            category.setDeleted(false);
            categoryRepository.save(category);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when  create new category " + e.getMessage());
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateCategory(CategoryDTO categoryDTO) throws UpdateDataFailException {
        boolean result;
        try {
            Category category = categoryConverter.convertCategoryToEntity(categoryDTO);
            Category tempCategory = getCategoryByIdAndBrand(category.getId());
            if (tempCategory == null) {
                LOGGER.info("Can't find the category to update ");
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            category.setCreateDate(tempCategory.getCreateDate());
            category.setUpdateDate(LocalDateTime.now());
            categoryRepository.save(category);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update new category " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
        return result;
    }

    @Override
    public Boolean delete(Long Id) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Category cate;
        Optional<Category> cateOptional = categoryRepository.findById(Id);
        if (cateOptional.isPresent()) {
            cate = cateOptional.get();
        } else {
            LOGGER.info("Can't find category with id " + Id);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        try {
            categoryRepository.deleteCategory(cate.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when delete category with id " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
        return result;
    }

    @Override
    public Boolean undelete(Long Id) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        try {
            Category cate;
            Optional<Category> cateOptional = categoryRepository.findById(Id);
            if (cateOptional.isPresent()) {
                cate = cateOptional.get();
            } else {
                LOGGER.info("Can't find category with id " + Id);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            categoryRepository.unDeleteCategory(cate.getId());
            result = true;
        } catch (Exception e) {
            LOGGER.info("Having error when update status category with id " + e.getMessage());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return result;
    }

    @Override
    public Boolean checkStatusCategory(Long Id) throws DataNotFoundException {
        boolean result;
        Category cate;
        Optional<Category> cateOptional = categoryRepository.findById(Id);
        if (cateOptional.isPresent()) {
            cate = cateOptional.get();
        } else {
            LOGGER.info("Can't find category with id " + Id);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        result = categoryRepository.checkStatusOfCategery(cate.getId());
        return result;
    }

    @Override
    public CategoryDTO getCategoryToShow(Long id) throws DataNotFoundException {
        CategoryDTO dto;
        try {
            Optional<Category> result = categoryRepository.findById(id);
            Category theCategory;
            if (result.isPresent()) {
                theCategory = result.get();
            } else {
                LOGGER.info("Can't find category with id " + id);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            dto = categoryConverter.convertCategoryToDto(theCategory) ;
        } catch (Exception e) {
            LOGGER.info("Having error when load the category " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_LOADED_FAIL);
        }
        return dto;
    }

    @Override
    public List<CategoryDTO> getListCategoryToShow(int pageNo, String valueSort) {
        List<CategoryDTO> listDTO;
        try {
            Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
            Page<Category> page = categoryRepository.findAll(pageable);
            listDTO = categoryConverter.toDTOList(page.getContent());
        } catch (Exception e) {
            LOGGER.info("Having error when load list category " + e.getMessage());
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public Category getCategoryByNameAndBrand(String name) throws DataNotFoundException {
        return categoryRepository.checkExistCategory(name);
    }

    @Override
    public Category getCategoryByIdAndBrand(Long id) throws DataNotFoundException {
        return categoryRepository.checkExistCategoryById(id);
    }

    @Override
    public Category findById(Long categoryid) throws DataNotFoundException {
        Optional<Category> result = categoryRepository.findById(categoryid);
        Category theCategory;
        if (result.isPresent()) {
            theCategory = result.get();
        } else {
            LOGGER.info("Can't find category with id " + categoryid);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return theCategory;
    }

}
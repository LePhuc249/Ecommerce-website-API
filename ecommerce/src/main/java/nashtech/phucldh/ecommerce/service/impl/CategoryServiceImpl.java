package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CategoryRepository;
import nashtech.phucldh.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() throws DataNotFoundException {
        List<Category> theListCategory = null;
        try {
            categoryRepository.findAll();
        } catch (Exception ex) {
            LOGGER.info("Can't find category ");
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return theListCategory;
    }

    @Override
    public void save(Category theCategory) throws CreateDataFailException {
        try {
            categoryRepository.save(theCategory);
        } catch (Exception ex) {
            LOGGER.info("Can't create new category ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
    }

    @Override
    public void delete(Long Id) throws DataNotFoundException, UpdateDataFailException {
        Category cate = null;
        Optional<Category> cateOptional = categoryRepository.findById(Id);
        if (cateOptional.isPresent()) {
            cate = cateOptional.get();
        } else {
            LOGGER.info("Can't find category with id " + Id);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        try {
            categoryRepository.deleteCategory(cate.getId());
        } catch (Exception ex) {
            LOGGER.info("Can't delete category with id " + Id);
            throw new UpdateDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
    }

    @Override
    public void undelete(Long Id) throws DataNotFoundException, UpdateDataFailException {
        Category cate = null;
        Optional<Category> cateOptional = categoryRepository.findById(Id);
        if (cateOptional.isPresent()) {
            cate = cateOptional.get();
        } else {
            LOGGER.info("Can't find category with id " + Id);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        try {
            categoryRepository.unDeleteCategory(cate.getId());
        } catch (Exception ex) {
            LOGGER.info("Can't update status category with id " + Id);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
    }

    @Override
    public Boolean checkStatusCategory(Long Id) throws DataNotFoundException {
        Category cate = null;
        Optional<Category> cateOptional = categoryRepository.findById(Id);
        if (cateOptional.isPresent()) {
            cate = cateOptional.get();
        } else {
            LOGGER.info("Can't find category with id " + Id);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        boolean result = false;
        result = categoryRepository.checkStatusOfCategery(cate.getId());
        return result;
    }

    @Override
    public Category getCategoryByNameAndBrand(String name, Long brandId) throws DataNotFoundException {
        Category result = categoryRepository.checkExistCategory(name, brandId);
        if (result == null) {
            LOGGER.info("Can't find category with name " + name + " and brand " + brandId);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return result;
    }

    @Override
    public Category findById(Long categoryid) throws DataNotFoundException {
        Optional<Category> result = categoryRepository.findById(categoryid);
        Category theCategory = null;
        if (result.isPresent()) {
            theCategory = result.get();
        } else {
            LOGGER.info("Can't find category with id " + categoryid);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return theCategory;
    }

}
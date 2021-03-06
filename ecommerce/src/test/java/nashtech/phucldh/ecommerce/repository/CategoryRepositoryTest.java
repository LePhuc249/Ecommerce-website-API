package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Category;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import org.junit.jupiter.api.Test;

import org.modelmapper.internal.util.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Test
    public void getAllCategory() {
        List<Category> list = categoryRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getCategoryById() throws DataNotFoundException {
        Category category;
        Optional<Category> result = categoryRepository.findById(Long.valueOf("1"));
        if (result.isPresent()){
            category = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        Assert.notNull(category);
    }

    @Test
    public void testFunctionGetByNameAndBrand() throws DataNotFoundException {
        Category category;
        Optional<Category> result = categoryRepository.findByName("Beer");
        if (result.isPresent()){
            category = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        Assert.notNull(category);
    }

    @Test
    public void addCategory() {
        Category category = new Category();
        category.setName("Wine");
        category.setCreateBy(Long.valueOf("1"));
        category.setDeleted(false);
        Assert.notNull(categoryRepository.save(category));
    }

    @Test
    public void updateCategory() throws DataNotFoundException {
        Category category;
        Optional<Category> result = categoryRepository.findById(Long.valueOf("98"));
        if (result.isPresent()){
            category = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        category.setName("New Name");
        Assert.notNull(categoryRepository.save(category));
    }

    @Test
    public void deleteCategory() throws DataNotFoundException {
        Category category;
        Optional<Category> result = categoryRepository.findById(Long.valueOf("98"));
        if (result.isPresent()){
            category = result.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(category.getId());
        Optional<Category> resultAfterDelete = categoryRepository.findById(Long.valueOf("98"));
        boolean checkExist = resultAfterDelete.isPresent();
        Assert.isTrue(!checkExist);
    }

    @Test
    public void checkStatusCategory() {
        boolean result = categoryRepository.checkStatusOfCategery(Long.valueOf("1"));
        Assert.isTrue(!result);
    }

}

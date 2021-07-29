package nashtech.phucldh.ecommerce.service;

import java.util.List;
import nashtech.phucldh.ecommerce.dto.Category.CategoryDTO;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface CategoryService {

    List<Category> findAll() throws DataNotFoundException;

    Category getCategoryByNameAndBrand(String name) throws DataNotFoundException;

    Category getCategoryByIdAndBrand(Long id) throws DataNotFoundException;

    Category findById(Long categoryid) throws DataNotFoundException;

    Boolean save(CategoryDTO categoryDTO) throws CreateDataFailException;

    Boolean updateCategory(CategoryDTO categoryDTO) throws UpdateDataFailException;

    Boolean delete(Long Id) throws DataNotFoundException, UpdateDataFailException;

    Boolean undelete(Long Id) throws DataNotFoundException, UpdateDataFailException;

    Boolean checkStatusCategory(Long Id) throws DataNotFoundException;

    CategoryDTO getCategoryToShow(Long id) throws DataNotFoundException;

    List<CategoryDTO> getListCategoryToShow(int pageNo, String valueSort);


}
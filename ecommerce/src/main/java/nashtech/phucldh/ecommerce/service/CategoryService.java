package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.dto.CategoryDTO;

import nashtech.phucldh.ecommerce.entity.Category;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import org.springframework.data.domain.Page;

public interface CategoryService {

    public List<Category> findAll() throws DataNotFoundException;

    public Category getCategoryByNameAndBrand(String name) throws DataNotFoundException;

    public Category getCategoryByIdAndBrand(Long id) throws DataNotFoundException;

    public Category findById(Long categoryid) throws DataNotFoundException;

    public Boolean save(CategoryDTO categoryDTO) throws CreateDataFailException;

    public Boolean updateCategory(CategoryDTO categoryDTO) throws UpdateDataFailException;

    public Boolean delete(Long Id) throws DataNotFoundException, UpdateDataFailException;

    public Boolean undelete(Long Id) throws DataNotFoundException, UpdateDataFailException;

    public Boolean checkStatusCategory(Long Id) throws DataNotFoundException;

    public Page<Category> getPaginationCategory(int pageNo, String valueSort);

}
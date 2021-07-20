package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface CategoryService {

    public List<Category> findAll() throws DataNotFoundException;

    public Category getCategoryByNameAndBrand(String name, Brand brand) throws DataNotFoundException;

    public Category findById(Long categoryid) throws DataNotFoundException;

    public void save(Category theCategory) throws CreateDataFailException;

    public void delete(Long Id) throws DataNotFoundException, UpdateDataFailException;

    public void undelete(Long Id) throws DataNotFoundException, UpdateDataFailException;

    public Boolean checkStatusCategory(Long Id) throws DataNotFoundException;

}
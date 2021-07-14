package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

public interface CategoryService {

	public List<Category> findAll();

	public Category getCategoryByName(String name) throws DataNotFoundException;

	public Category findById(Integer categoryid) throws DataNotFoundException;

	public void save(Category theCategory);

	public void delete(Integer categoryId);

}

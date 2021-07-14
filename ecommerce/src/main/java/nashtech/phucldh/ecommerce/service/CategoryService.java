package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Category;

public interface CategoryService {
	
	public List<Category> findAll();
	
	public Category getCategoryByName(String name);
	
	public void save(Category theCategory);
	
	public void delete(String categoryId);

	public Category findById(String categoryid);
}

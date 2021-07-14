package nashtech.phucldh.ecommerce.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryRestController {
	
//	private CategoryService categoryService;
//	
//	@Autowired
//	public CategoryRestController(CategoryService categoryService) {
//		this.categoryService = categoryService;
//	}
//	
//	@GetMapping
//	public List<Category> getListCategory() {
//		return categoryService.findAll();
//	}
	
//	@GetMapping("/{categoryName}")
//	public Category getCategoryByName(@PathVariable String name) {
//		Category theCategory = categoryService.getCategoryByName(name);
//		if (theCategory == null) {
//			throw new CategoryNotFoundException("Category name: " + name + " not found!");
//		}
//		return theCategory;
//	}
//	
//	@GetMapping("/{categoryId}")
//	public Category getCategoryById(@PathVariable String id) {
//		Category theCategory = categoryService.findById(id);
//		if (theCategory == null) {
//			throw new CategoryNotFoundException("Category id: " + id + " not found!");
//		}
//		return theCategory;
//	}
//	
//	@PostMapping
//	public void addCategory(@RequestBody Category newCategory) {
//		categoryService.save(newCategory);
//	}
//
//	@PutMapping
//	public void updateCategory(@RequestBody Category updateCategory) {
//		Category tempCategory = categoryService.findById(updateCategory.getClass());
//		if (tempCategory == null) {
//			throw new CategoryNotFoundException("Category id: " + updateCategory.getCategoryid() + " not found!");
//		}
//		categoryService.save(updateCategory);
//	}
//
//	@DeleteMapping("/{categoryId}")
//	public String deleteCategory(@PathVariable String categoryId) {
//		Category tempCategory = categoryService.findById(categoryId);
//		if (tempCategory == null) {
//			throw new CategoryNotFoundException("Category id: " + categoryId + " not found!");
//		}
//		categoryService.delete(categoryId);
//		return "Delete category id: " + categoryId;
//	}
	
}

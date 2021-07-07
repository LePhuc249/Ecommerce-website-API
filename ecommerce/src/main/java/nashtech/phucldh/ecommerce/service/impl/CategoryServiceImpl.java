package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.reponsitory.CategoryReponsitory;
import nashtech.phucldh.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryReponsitory categoryRepository;

	@Override
	public List<Category> findAll() {
		List<Category> theListCategory = categoryRepository.findAll();
		return theListCategory;
	}

}

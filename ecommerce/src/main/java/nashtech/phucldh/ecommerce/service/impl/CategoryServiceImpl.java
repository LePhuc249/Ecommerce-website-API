package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CategoryReponsitory;
import nashtech.phucldh.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryReponsitory categoryRepository;

	@Override
	public List<Category> findAll() {
		List<Category> theListCategory = categoryRepository.findAll();
		return theListCategory;
	}

	@Override
	public void save(Category theCategory) {
		categoryRepository.save(theCategory);
	}

	@Override
	public void delete(Integer categoryId) {
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public Category getCategoryByName(String name) throws DataNotFoundException {
		Optional<Category> result = categoryRepository.findByName(name);
		Category theCategory = null;
		if (result.isPresent()) {
			theCategory = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theCategory;
	}

	@Override
	public Category findById(Integer categoryid) throws DataNotFoundException {
		Optional<Category> result = categoryRepository.findById(categoryid);
		Category theCategory = null;
		if (result.isPresent()) {
			theCategory = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theCategory;
	}

}

package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.CategoryRepository;
import nashtech.phucldh.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() throws DataNotFoundException {
		List<Category> theListCategory = null;
		try {
			categoryRepository.findAll();
		} catch(Exception ex) {
			throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
		}
		return theListCategory;
	}

	@Override
	public void save(Category theCategory) throws CreateDataFailException {
		try {
			categoryRepository.save(theCategory);
		} catch (Exception ex) {
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
			throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
		}
		try {
			categoryRepository.deleteCategory(cate.getId());
		} catch(Exception ex) {
			throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
		}
	}

	@Override
	public void undelete(Long Id) throws DataNotFoundException, UpdateDataFailException {
		Category cate = null;
		Optional<Category> cateOptional = categoryRepository.findById(Id);
		if (cateOptional.isPresent()) {
			cate = cateOptional.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
		}
		try {
			categoryRepository.unDeleteCategory(cate.getId());
		} catch(Exception ex) {
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
			throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
		}
		boolean result = false;
		result = categoryRepository.checkStatusOfCategery(cate.getId());
		return result;
	}

	@Override
	public Category getCategoryByNameAndBrand(String name, Brand brand) throws DataNotFoundException {
		Optional<Category> result = categoryRepository.findByNameAndBrand(name, brand);
		Category theCategory = null;
		if (result.isPresent()) {
			theCategory = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theCategory;
	}

	@Override
	public Category findById(Long categoryid) throws DataNotFoundException {
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

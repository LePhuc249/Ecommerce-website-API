package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.ProductRepository;
import nashtech.phucldh.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		List<Product> result = productRepository.findAll();
		return result;
	}

	@Override
	public List<Product> findAllForCustomer() {
		List<Product> result = productRepository.getListForCustomer();
		return result;
	}

	@Override
	public List<Product> findByNameOrCategory(String itemname, String categoryid) {
		List<Product> result = productRepository.searchByNameOrCategory(itemname, categoryid);
		return result;
	}

	@Override
	public List<Product> findByNameOrCategoryForCustomer(String itemname, String categoryid) {
		List<Product> result = productRepository.searchByNameOrCategoryForCustomer(itemname, categoryid);
		return result;
	}

	@Override
	public boolean checkExistProduct(String itemname, String img, String discription, String productName) {
		boolean result = false;
		Product theProduct = productRepository.checkExistProduct(itemname, img, discription, productName);
		if (theProduct != null) {
			result = true;
		}
		return result;
	}

	@Override
	public Product getProductById(Integer productId) throws DataNotFoundException {
		Optional<Product> result = productRepository.findById(productId);
		Product theProduct = null;
		if (result.isPresent()) {
			theProduct = result.get();
		} else {
			throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
		}
		return theProduct;
	}

	@Override
	public void saveProduct(Product theProduct) {
		productRepository.save(theProduct);
	}

	@Override
	public void deleteProduct(Integer productId) {
		productRepository.deactiveProduct(productId);
	}

	@Override
	public void activeProduct(Integer productId) {
		productRepository.activeProduct(productId);
	}

}

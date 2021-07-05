package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public List<Product> findAllForCustomer();
	
	public List<Product> findByNameOrCategory();
	
	public List<Product> findByNameOrCategoryForCustomer();
	
	public boolean checkExistProduct();
	
	public Product getProductById(String productId);
	
	public Product checkProductToAdd(String productId);
	
	public void saveProduct(Product theProduct);
	
	public void deleteProduct(String productId);
}

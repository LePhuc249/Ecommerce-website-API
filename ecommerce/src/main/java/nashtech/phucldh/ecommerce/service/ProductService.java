package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Product;

public interface ProductService {
	
	public List<Product> findAll();
	
	public List<Product> findAllForCustomer();
	
	public List<Product> findByNameOrCategory(String itemname, String categoryid);
	
	public List<Product> findByNameOrCategoryForCustomer(String itemname, String categoryid);
	
	public boolean checkExistProduct(String itemname, String img, String discription, String productName);
	
	public Product getProductById(Integer productId);
	
	public void saveProduct(Product theProduct);
	
	public void deleteProduct(Integer productId);
	
	public void activeProduct(Integer productId);
}

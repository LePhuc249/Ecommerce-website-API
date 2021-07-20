package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

public interface ProductService {

    public List<Product> findAllProductForAdmin() throws DataNotFoundException;

    public List<Product> findAllForCustomer() throws DataNotFoundException;

    public List<Product> findByNameOrCategory(String itemname, Long categoryid) throws DataNotFoundException;

    public List<Product> findByNameOrCategoryForCustomer(String itemname, Long categoryid) throws DataNotFoundException;

    public boolean checkExistProduct(String name, String discription, Long brand) throws DataNotFoundException;

    public Product getProductById(Long productId) throws DataNotFoundException;

    public void saveProduct(Product theProduct) throws CreateDataFailException;

    public void deleteProduct(Long productId) throws DataNotFoundException, DeleteDataFailException;

    public void activeProduct(Long productId) throws DataNotFoundException, UpdateDataFailException;

    public Product getProductToAdd(Long id) throws DataNotFoundException;

    public void updateQuantity(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException;

    public void updateCounter(Long id, int counter) throws DataNotFoundException, UpdateDataFailException;

    public int getQuantityOfProduct(Long id) throws DataNotFoundException;

    public int getCounterOfProduct(Long id) throws DataNotFoundException;

    public int getQuantityOfProductForCustomer(Long id) throws DataNotFoundException;

    public String getNameProductById(Long id) throws DataNotFoundException;

}
package nashtech.phucldh.ecommerce.service;

import java.util.List;

import nashtech.phucldh.ecommerce.dto.CreateProductDTO;
import nashtech.phucldh.ecommerce.dto.UpdateProductDTO;

import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import org.springframework.data.domain.Page;

public interface ProductService {

    public List<Product> findAllProductForAdmin() throws DataNotFoundException;

    public List<Product> findAllForCustomer() throws DataNotFoundException;

    public List<Product> findByNameOrCategory(String itemname, Long categoryid) throws DataNotFoundException;

    public List<Product> findByNameOrCategoryForCustomer(String itemname, Long categoryid) throws DataNotFoundException;

    public Product getProductById(Long productId) throws DataNotFoundException;

    public Product getProductToAdd(Long id) throws DataNotFoundException;

    public Boolean checkExistProduct(String name, String discription, Long brand) throws DataNotFoundException;

    public Boolean saveProduct(CreateProductDTO dtoProduct) throws CreateDataFailException;

    public Boolean updateProduct(UpdateProductDTO dtoProduct) throws CreateDataFailException;

    public Boolean deleteProduct(Long productId) throws DataNotFoundException, DeleteDataFailException;

    public Boolean activeProduct(Long productId) throws DataNotFoundException, UpdateDataFailException;

    public int getQuantityOfProduct(Long id) throws DataNotFoundException;

    public int getCounterOfProduct(Long id) throws DataNotFoundException;

    public int getQuantityOfProductForCustomer(Long id) throws DataNotFoundException;

    public String getNameProductById(Long id) throws DataNotFoundException;

    public Page<Product> getPaginationProductForAdmin(int pageNo, String valueSort);

    public Page<Product> getPaginationProductForCustomer(int pageNo, String valueSort);

}
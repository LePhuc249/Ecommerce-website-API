package nashtech.phucldh.ecommerce.service;

import java.util.List;
import nashtech.phucldh.ecommerce.dto.Product.CreateProductDTO;
import nashtech.phucldh.ecommerce.dto.Product.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.Product.UpdateProductDTO;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import org.springframework.data.domain.Page;

public interface ProductService {

    List<Product> findByNameOrCategory(String itemname, Long categoryid) throws DataNotFoundException;

    List<Product> findByNameOrCategoryForCustomer(String itemname, Long categoryid) throws DataNotFoundException;

    Product getProductById(Long productId) throws DataNotFoundException;

    Product getProductToAdd(Long id) throws DataNotFoundException;

    Boolean checkExistProduct(String name, String discription, Long brand) throws DataNotFoundException;

    Boolean saveProduct(CreateProductDTO dtoProduct) throws CreateDataFailException;

    Boolean updateProduct(UpdateProductDTO dtoProduct) throws CreateDataFailException;

    Boolean deleteProduct(Long productId) throws DataNotFoundException, DeleteDataFailException;

    Boolean activeProduct(Long productId) throws DataNotFoundException, UpdateDataFailException;

    String getNameProductById(Long id) throws DataNotFoundException;

    Page<Product> getPaginationProductForAdmin(int pageNo, String valueSort);

    Page<Product> getPaginationProductForCustomer(int pageNo, String valueSort);

    ProductDetailDTO getProductDetailByID(Long productId) throws DataNotFoundException;

    List<ProductDetailDTO> getListProductForAdmin(int pageNo, String valueSort);

    List<ProductDetailDTO> getListProductForCustomer(int pageNo, String valueSort);

}
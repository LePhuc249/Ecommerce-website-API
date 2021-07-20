package nashtech.phucldh.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.BrandRepository;
import nashtech.phucldh.ecommerce.reponsitory.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.ProductRepository;
import nashtech.phucldh.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Product> findAllProductForAdmin() throws DataNotFoundException {
        List<Product> result = null;
        try {
            result = productRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Can't find all product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public List<Product> findAllForCustomer() throws DataNotFoundException {
        List<Product> result = null;
        try {
            result = productRepository.getListForCustomer();
        } catch (Exception e) {
            LOGGER.info("Can't find all product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public List<Product> findByNameOrCategory(String itemname, Long categoryid) throws DataNotFoundException {
        List<Product> result = null;
        try {
            result = productRepository.searchByNameOrCategory(itemname, categoryid);
        } catch (Exception e) {
            LOGGER.info("Can't find product by name or category ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public List<Product> findByNameOrCategoryForCustomer(String itemname, Long categoryid) throws DataNotFoundException {
        List<Product> result = null;
        try {
            result = productRepository.searchByNameOrCategoryForCustomer(itemname, categoryid);
        } catch (Exception e) {
            LOGGER.info("Can't find product by name or category for customer");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public boolean checkExistProduct(String itemname, String discription, Long brandId) throws DataNotFoundException {
        Brand brand = null;
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);
        if (optionalBrand.isPresent()) {
            brand = optionalBrand.get();
        } else {
            LOGGER.info("Can't find brand");
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        Category cate = null;
        Optional<Category> optionalCate = categoryRepository.findByBrand(brand);
        if (optionalCate.isPresent()) {
            cate = optionalCate.get();
        } else {
            LOGGER.info("Can't find category ");
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        boolean result = false;
        Product theProduct = productRepository.checkExistProduct(itemname, discription, brandId, cate.getId());
        if (theProduct != null) {
            result = true;
        }
        return result;
    }

    @Override
    public Product getProductById(Long productId) throws DataNotFoundException {
        Optional<Product> result = productRepository.findById(productId);
        Product theProduct = null;
        if (result.isPresent()) {
            theProduct = result.get();
        } else {
            LOGGER.info("Can't find product by id " + productId);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return theProduct;
    }

    @Override
    public void saveProduct(Product theProduct) throws CreateDataFailException {
        try {
            productRepository.save(theProduct);
        } catch (Exception e) {
            LOGGER.info("Can't create product ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
    }

    @Override
    public void deleteProduct(Long productId) throws DataNotFoundException, DeleteDataFailException {
        Optional<Product> result = productRepository.findById(productId);
        Product theProduct = null;
        if (result.isPresent()) {
            theProduct = result.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        try {
            productRepository.deleteProduct(productId);
        } catch (Exception ex) {
            LOGGER.info("Can't delete product ");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
        }
    }

    @Override
    public void activeProduct(Long productId) throws DataNotFoundException, UpdateDataFailException {
        Optional<Product> result = productRepository.findById(productId);
        Product theProduct = null;
        if (result.isPresent()) {
            theProduct = result.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        try {
            productRepository.unDeleteProduct(productId);
        } catch (Exception ex) {
            LOGGER.info("Can't update product ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
    }

    @Override
    public Product getProductToAdd(Long id) throws DataNotFoundException {
        Product product = null;
        try {
            product = productRepository.checkProductToAddToCart(id);
        } catch (Exception e) {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public void updateQuantity(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        try {
            productRepository.updateQuantityProduct(product.getId(), quantity);
        } catch (Exception e) {
            LOGGER.info("Can't update product ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
    }

    @Override
    public void updateCounter(Long id, int counter) throws DataNotFoundException, UpdateDataFailException {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        try {
            productRepository.updateCounterProduct(product.getId(), counter);
        } catch (Exception e) {
            LOGGER.info("Can't update product ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
    }

    @Override
    public int getQuantityOfProduct(Long id) throws DataNotFoundException {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        int quantity = 0;
        try {
            quantity = productRepository.getQuantityOfProduct(product.getId());
        } catch (Exception e) {
            LOGGER.info("Can't update product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return quantity;
    }

    @Override
    public int getCounterOfProduct(Long id) throws DataNotFoundException {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        int counter = 0;
        try {
            counter = productRepository.getCounterOfProduct(product.getId());
        } catch (Exception e) {
            LOGGER.info("Can't get counter product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return counter;
    }

    @Override
    public int getQuantityOfProductForCustomer(Long id) throws DataNotFoundException {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        int quantity = 0;
        try {
            quantity = productRepository.getQuantityOfProductForCustomer(product.getId());
        } catch (Exception e) {
            LOGGER.info("Can't get counter product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return quantity;
    }

    @Override
    public String getNameProductById(Long id) throws DataNotFoundException {
        Product product = null;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product name ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        String name = null;
        try {
            name = productRepository.getNameById(product.getId());
        } catch (Exception e) {
            LOGGER.info("Can't find name product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return name;
    }

}
package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.converter.ProductConverter;

import nashtech.phucldh.ecommerce.dto.CreateProductDTO;
import nashtech.phucldh.ecommerce.dto.UpdateProductDTO;

import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import nashtech.phucldh.ecommerce.repository.BrandRepository;
import nashtech.phucldh.ecommerce.repository.CategoryRepository;
import nashtech.phucldh.ecommerce.repository.ProductRepository;

import nashtech.phucldh.ecommerce.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductConverter productConverter;

    @Override
    public List<Product> findAllProductForAdmin() throws DataNotFoundException {
        List<Product> result;
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
        List<Product> result;
        try {
            result = productRepository.findByIsDeletedAndQuantityGreaterThan(false, 0);
        } catch (Exception e) {
            LOGGER.info("Can't find all product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public List<Product> findByNameOrCategory(String itemname, Long categoryid) throws DataNotFoundException {
        List<Product> result;
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
        List<Product> result;
        try {
            result = productRepository.searchByNameOrCategoryForCustomer(itemname, categoryid);
        } catch (Exception e) {
            LOGGER.info("Can't find product by name or category for customer");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return result;
    }

    @Override
    public Boolean checkExistProduct(String itemname, String discription, Long brandId) throws DataNotFoundException {
        boolean result = false;
        Product theProduct = productRepository.checkExistProduct(itemname, discription, brandId);
        if (theProduct != null) {
            result = true;
        }
        return result;
    }

    @Override
    public Boolean saveProduct(CreateProductDTO dtoProduct) throws CreateDataFailException {
        boolean result;
        try {
            Product product = productConverter.convertCreateProductDTOToEntity(dtoProduct);
            boolean checkExistProduct = checkExistProduct(product.getName(), product.getDescription(), product.getBrand().getId());
            if (checkExistProduct) {
                LOGGER.info("Product have been existed");
                throw new DuplicateDataException(ErrorCode.ERR_PRODUCT_EXISTED);
            }
            product.setCreateDate(LocalDateTime.now());
            product.setDeleted(false);
            productRepository.save(product);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't create product ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateProduct(UpdateProductDTO dtoProduct) throws CreateDataFailException {
        boolean result;
        try {
            Product product = productConverter.convertUpdateProductDTOToEntity(dtoProduct);
            Optional<Product> productOptional = productRepository.findById(product.getId());
            if (!productOptional.isPresent()) {
                LOGGER.info("Can't find product ");
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            product.setUpdateDate(LocalDateTime.now());
            productRepository.save(product);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't create product ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return result;
    }

    @Override
    public Product getProductById(Long productId) throws DataNotFoundException {
        Product theProduct = productRepository.getProductByID(productId);
        if (theProduct == null) {
            LOGGER.info("Can't find product with the product id: " + productId);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        } else {
            return theProduct;
        }
    }

    @Override
    public Boolean deleteProduct(Long productId) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product theProduct = null;
        if (optionalProduct.isPresent()) {
            theProduct = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        try {
            productRepository.deleteProduct(productId);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't delete product ");
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
        }
        return result;
    }

    @Override
    public Boolean activeProduct(Long productId) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product theProduct = null;
        if (optionalProduct.isPresent()) {
            theProduct = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_ROLE_NOT_FOUND);
        }
        try {
            productRepository.unDeleteProduct(productId);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't update product ");
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return result;
    }

    @Override
    public Product getProductToAdd(Long id) throws DataNotFoundException {
        Product product;
        try {
            product = productRepository.checkProductToAddToCart(id);
        } catch (Exception e) {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public int getQuantityOfProduct(Long id) throws DataNotFoundException {
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        int quantity;
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
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        int counter;
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
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        int quantity;
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
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            LOGGER.info("Can't find product name ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        String name;
        try {
            name = productRepository.getNameById(product.getId());
        } catch (Exception e) {
            LOGGER.info("Can't find name product ");
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return name;
    }

    @Override
    public Page<Product> getPaginationProductForAdmin(int pageNo, String valueSort) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(valueSort).ascending());
        Page<Product> page = productRepository.findAll(pageable);
        return page;
    }

    @Override
    public Page<Product> getPaginationProductForCustomer(int pageNo, String valueSort) {
        Pageable pageable = PageRequest.of(pageNo - 1, 10, Sort.by(valueSort).ascending());
        Page<Product> page = productRepository.findByIsDeletedAndQuantityGreaterThan(false, 0, pageable);
        return page;
    }

}
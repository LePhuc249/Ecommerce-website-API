package nashtech.phucldh.ecommerce.repository;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.reponsitory.BrandRepository;
import nashtech.phucldh.ecommerce.reponsitory.CategoryRepository;
import nashtech.phucldh.ecommerce.reponsitory.ProductRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void getAll() {
        List<Product> list = productRepository.findAll();
        Assert.notNull(list);
    }

    @Test
    public void getById() {
        Product product = null;
        Optional<Product> result = productRepository.findById(Long.valueOf("1"));
    }

    @Test
    public void addProduct() {
        Brand brand = brandRepository.findById(Long.valueOf("1")).get();
        Category category = categoryRepository.findById(Long.valueOf("1")).get();
        Product product = new Product();
        product.setName("New Product");
        product.setShortdescription("Test short ");
        product.setDescription("Test description");
        product.setPrice(Float.valueOf(10));
        product.setBrand(brand);
        product.setQuantity(200);
        product.setCategory(category);
        product.setCounter(1);
        product.setDeleted(false);
        Assert.notNull(productRepository.save(product));
    }

    @Test
    public void updateProduct() {
        Product product = productRepository.findById(Long.valueOf("113")).get();
        product.setCounter(10);
        Assert.notNull(productRepository.save(product));
    }

    @Test
    public void deleteProduct() {
        Product product = productRepository.findById(Long.valueOf("115")).get();
        productRepository.deleteById(product.getId());
        Optional<Product> result = productRepository.findById(Long.valueOf("115"));
        boolean checkExist = result.isPresent();
        Assert.isTrue(!checkExist);
    }

    @Test
    public void checkFunctionCheckExistProduct() {
        Product product = productRepository.checkExistProduct("Corona", "Corona beer", Long.valueOf("9"));
        boolean result = false;
        if (product != null) {
            result = true;
        }
        Assert.isTrue(result);
    }

    @Test
    public void checkFunctionGetListForCustomer() {
        List<Product> list = productRepository.getListForCustomer();
        boolean result = false;
        if (list.size() > 0) {
            result = true;
        }
        Assert.isTrue(result);
    }

    @Test
    public void checkFunctionSearchByNameOrCategory() {
        List<Product> list = productRepository.searchByNameOrCategory("Cozy", Long.valueOf("30"));
        boolean result = false;
        if (list.size() > 0) {
            result = true;
        }
        Assert.isTrue(result);
    }

    @Test
    public void checkFuntionSearchByNameOrCategoryForCustomer() {
        List<Product> list = productRepository.searchByNameOrCategoryForCustomer("Cozy", Long.valueOf("30"));
        boolean result = false;
        if (list.size() > 0) {
            result = true;
        }
        Assert.isTrue(result);
    }
}

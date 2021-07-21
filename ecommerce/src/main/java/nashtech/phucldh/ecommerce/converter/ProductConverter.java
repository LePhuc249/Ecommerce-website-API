package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.PagingProductDTO;
import nashtech.phucldh.ecommerce.dto.ProductDTO;
import nashtech.phucldh.ecommerce.dto.ProductDetailDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.reponsitory.BrandRepository;
import nashtech.phucldh.ecommerce.reponsitory.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDetailDTO convertProductDetailToDto(Product product) throws ConvertEntityDTOException {
        try {
            ProductDetailDTO productDetailDTO = modelMapper.map(product, ProductDetailDTO.class);
            productDetailDTO.setBrand(product.getBrand().getId());
            productDetailDTO.setCategory(product.getCategory().getId());
            return productDetailDTO;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Product to ProductDetailDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public PagingProductDTO convertPagingProductToDto(Product product) throws ConvertEntityDTOException {
        try {
            PagingProductDTO pagingProductDTO = modelMapper.map(product, PagingProductDTO.class);
            pagingProductDTO.setBrand(product.getBrand().getId());
            pagingProductDTO.setCategory(product.getCategory().getId());
            return pagingProductDTO;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Product to PagingProductDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public Product convertProductDTOToEntity(ProductDTO dtoProduct) throws ConvertEntityDTOException {
        try {
            Brand brand = null;
            Category category = null;
            Product product = modelMapper.map(dtoProduct, Product.class);
            Optional<Brand> brandOptional = brandRepository.findById(dtoProduct.getBrand());
            if (brandOptional.isPresent()) {
                brand = brandOptional.get();
            } else {
                LOGGER.info("Can't find brand of product");
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            Optional<Category> categoryOptional = categoryRepository.findById(dtoProduct.getCategory());
            if (categoryOptional.isPresent()) {
                category = categoryOptional.get();
            } else {
                LOGGER.info("Can't find category of product");
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            product.setBrand(brand);
            product.setCategory(category);
            return product;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert ProductDTO to Product");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}
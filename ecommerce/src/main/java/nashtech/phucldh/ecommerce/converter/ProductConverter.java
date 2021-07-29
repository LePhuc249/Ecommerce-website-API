package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.dto.Product.CreateProductDTO;
import nashtech.phucldh.ecommerce.dto.Product.PagingProductDTO;
import nashtech.phucldh.ecommerce.dto.Product.ProductDTO;
import nashtech.phucldh.ecommerce.dto.Product.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.Product.UpdateProductDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.repository.BrandRepository;
import nashtech.phucldh.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            return productDetailDTO;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Product to ProductDetailDTO. Reason: " + ex.getMessage());
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
            Brand brand;
            Category category;
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

    public Product convertCreateProductDTOToEntity(CreateProductDTO dtoProduct) throws ConvertEntityDTOException {
        try {
            Brand brand;
            Category category;
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

    public Product convertUpdateProductDTOToEntity(UpdateProductDTO dtoProduct) throws ConvertEntityDTOException {
        try {
            Brand brand;
            Category category;
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

    public ProductDetailDTO toDTO(Product entity) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setShortDescription(entity.getShortDescription());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setCreateDate(entity.getCreateDate());
        dto.setUpdateDate(entity.getUpdateDate());
        dto.setBrandId(entity.getBrand().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setCounter(entity.getCounter());
        dto.setDeleted(entity.isDeleted());
        return dto;
    }


    public List<ProductDetailDTO> toDTOList(List<Product> entityList) {
        List<ProductDetailDTO> dtoList = entityList.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());
        return dtoList;
    }

}
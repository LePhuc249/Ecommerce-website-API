package nashtech.phucldh.ecommerce.restcontroller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;
import nashtech.phucldh.ecommerce.converter.BrandConverter;
import nashtech.phucldh.ecommerce.converter.CategoryConverter;
import nashtech.phucldh.ecommerce.converter.CouponsConverter;
import nashtech.phucldh.ecommerce.converter.OrganizationAddressConverter;
import nashtech.phucldh.ecommerce.converter.OrganizationConverter;
import nashtech.phucldh.ecommerce.converter.ProductConverter;
import nashtech.phucldh.ecommerce.dto.BrandDTO;
import nashtech.phucldh.ecommerce.dto.CategoryDTO;
import nashtech.phucldh.ecommerce.dto.CouponsDTO;
import nashtech.phucldh.ecommerce.dto.OrganizationAddressDTO;
import nashtech.phucldh.ecommerce.dto.OrganizationDTO;
import nashtech.phucldh.ecommerce.dto.ProductDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.DuplicateDataException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.service.BrandService;
import nashtech.phucldh.ecommerce.service.CategoryService;
import nashtech.phucldh.ecommerce.service.CouponsService;
import nashtech.phucldh.ecommerce.service.OrganizationAddressService;
import nashtech.phucldh.ecommerce.service.OrganizationService;
import nashtech.phucldh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manager")
public class ManagerRestController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationAddressService organizationAddressService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CouponsService couponsService;

    @Autowired
    private BrandConverter brandConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private OrganizationConverter organizationConverter;

    @Autowired
    private OrganizationAddressConverter organizationAddressConverter;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private CouponsConverter couponsConverter;

    public ResponseEntity<ResponseDTO> createNewOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Organization organization = organizationConverter.convertOrganizationDTOToEntity(organizationDTO);
            Organization tempOrganization = organizationService.checkExistedOrganization(organization.getName(), organization.getImageOrganization().getId());
            if (tempOrganization != null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_EXISTED);
                throw new DuplicateDataException(ErrorCode.ERR_ORGANIZATION_EXISTED);
            }
            organization.setDeleted(false);
            organizationService.createOrganization(organization);
            response.setSuccessCode(SuccessCode.ORGANIZATION_CREATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
            throw new CreateDataFailException("Manager Rest Controller: " + ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Organization organization = organizationConverter.convertOrganizationDTOToEntity(organizationDTO);
            Organization tempOrganization = organizationService.getOrganizationByID(organization.getId());
            if (tempOrganization == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            organizationService.updateOrganization(organization);
            response.setSuccessCode(SuccessCode.ORGANIZATION_UPDATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> deactiveOrganization(@PathVariable int organizationId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(organizationId));
            Organization tempOrganization = organizationService.getOrganizationByID(id);
            if (tempOrganization == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            organizationService.deleteOrganization(id);
            response.setSuccessCode(SuccessCode.ORGANIZATION_DELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> activeOrganization(@PathVariable int organizationId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(organizationId));
            Organization tempOrganization = organizationService.getOrganizationByID(id);
            if (tempOrganization == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            organizationService.activeOrganization(id);
            response.setSuccessCode(SuccessCode.ORGANIZATION_UNDELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getOrganization(@PathVariable int organizationId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(organizationId));
            Organization organization = organizationService.getOrganizationByID(id);
            if (organization == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            response.setData(organization);
            response.setSuccessCode(SuccessCode.GET_ORGANIZATION_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAllOrganization() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Organization> listOrganization = organizationService.getAllOrganizations();
            if (listOrganization == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            response.setData(listOrganization);
            response.setSuccessCode(SuccessCode.GET_ALL_ORGANIZATION_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> createNewBrand(@Valid @RequestBody BrandDTO brandDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Brand brand = brandConverter.convertBrandDTOToEntity(brandDTO);
            Brand tempBrand = brandService.checkExistedBrand(brand.getName(), brand.getOrganization().getId());
            if (tempBrand != null) {
                response.setErrorCode(ErrorCode.ERR_BRAND_EXISTED);
                throw new DuplicateDataException(ErrorCode.ERR_BRAND_EXISTED);
            }
            brandService.addNewBrand(brand);
            response.setSuccessCode(SuccessCode.BRAND_CREATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_BRAND_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> updateBrand(@Valid @RequestBody BrandDTO brandDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Brand brand = brandConverter.convertBrandDTOToEntity(brandDTO);
            Brand tempBrand = brandService.checkExistedBrand(brand.getName(), brand.getOrganization().getId());
            if (tempBrand == null) {
                response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            brandService.updateBrand(brand);
            response.setSuccessCode(SuccessCode.BRAND_UPDATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_BRAND_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> deleteBrand(@PathVariable int brandId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(brandId));
            Brand brand = brandService.getBrand(id);
            if (brand == null) {
                response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            brandService.deleteBrand(id);
            response.setSuccessCode(SuccessCode.BRAND_DELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_BRAND_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getBrand(@PathVariable int brandId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(brandId));
            Brand brand = brandService.getBrand(id);
            if (brand == null) {
                response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            response.setData(brand);
            response.setSuccessCode(SuccessCode.GET_BRAND_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAllBrand() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Brand> listBrand = brandService.getAllBrand();
            if (listBrand.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
            }
            response.setData(listBrand);
            response.setSuccessCode(SuccessCode.GET_ALL_BRAND_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> createNewCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Category category = categoryConverter.convertCategoryToEntity(categoryDTO);
            Category tempCategory = categoryService.getCategoryByNameAndBrand(category.getName(), category.getBrand().getId());
            if (tempCategory != null) {
                response.setErrorCode(ErrorCode.ERR_CATEGORY_EXISTED);
                throw new DuplicateDataException(ErrorCode.ERR_CATEGORY_EXISTED);
            }
            categoryService.save(category);
            response.setSuccessCode(SuccessCode.CATEGORY_CREATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Category category = categoryConverter.convertCategoryToEntity(categoryDTO);
            Category tempCategory = categoryService.getCategoryByNameAndBrand(category.getName(), category.getBrand().getId());
            if (tempCategory == null) {
                response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            categoryService.save(category);
            response.setSuccessCode(SuccessCode.CATEGORY_UPDATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable int categoryId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(categoryId));
            Category category = categoryService.findById(id);
            if (category == null) {
                response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            categoryService.delete(id);
            response.setSuccessCode(SuccessCode.CATEGORY_DELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> activeCategory(@PathVariable int categoryId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(categoryId));
            Category category = categoryService.findById(id);
            if (category == null) {
                response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            categoryService.undelete(id);
            response.setSuccessCode(SuccessCode.CATEGORY_UNDELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getCategory(@PathVariable int categoryId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(categoryId));
            Category category = categoryService.findById(id);
            if (category == null) {
                response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            response.setData(category);
            response.setSuccessCode(SuccessCode.GET_CATEGORY_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAllCategory() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Category> list = categoryService.findAll();
            if (list.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            }
            response.setData(list);
            response.setSuccessCode(SuccessCode.GET_ALL_CATEGORY_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> createNewAddressForOrganization(@RequestBody OrganizationAddressDTO organizationAddressDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            OrganizationAddress organizationAddress = organizationAddressConverter.convertOrganizationDTOToEntity(organizationAddressDTO);
            OrganizationAddress temp = organizationAddressService.getOrganizationAddress(organizationAddress.getId(), organizationAddress.getAddress());
            if (temp != null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_EXISTED);
                throw new DuplicateDataException(ErrorCode.ERR_ORGANIZATION_ADDRESS_EXISTED);
            }
            organizationAddressService.createNewAddress(organizationAddress);
            response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_CREATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> updateAddressForOrganization(@RequestBody OrganizationAddressDTO organizationAddressDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            OrganizationAddress organizationAddress = organizationAddressConverter.convertOrganizationDTOToEntity(organizationAddressDTO);
            OrganizationAddress temp = organizationAddressService.getOrganizationAddress(organizationAddress.getId(), organizationAddress.getAddress());
            if (temp == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            organizationAddressService.updateAddress(organizationAddress);
            response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_UPDATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> deleteAddressForOrganization(@PathVariable Long organizationAddressId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            OrganizationAddress temp = organizationAddressService.findAddressById(organizationAddressId);
            if (temp == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            organizationAddressService.deleteAddress(organizationAddressId);
            response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_DELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAddressForOrganization(@PathVariable Long organizationAddressId) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<OrganizationAddress> listAddress = organizationAddressService.getListAddressOfOrganization(organizationAddressId);
            if (listAddress == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            response.setData(listAddress);
            response.setSuccessCode(SuccessCode.GET_ORGANIZATION_ADDRESS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getAllAddressForOrganization(@PathVariable Long organizationAddressId) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<OrganizationAddress> listAddress = organizationAddressService.findAllAddress();
            if (listAddress == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            response.setData(listAddress);
            response.setSuccessCode(SuccessCode.GET_ALL_ORGANIZATION_ADDRESS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> createNewCoupons(@RequestBody CouponsDTO dtoCoupon) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Coupons coupons = couponsConverter.convertCouponsDTOToEntity(dtoCoupon);
            Coupons tempCoupons = couponsService.getCouponByCode(dtoCoupon.getCode());
            if (tempCoupons != null) {
                response.setErrorCode(ErrorCode.ERR_COUPONS_EXISTED);
                throw new DuplicateDataException(ErrorCode.ERR_COUPONS_EXISTED);
            }
            couponsService.createCoupon(coupons);
            response.setSuccessCode(SuccessCode.COUPONS_CREATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_COUPONS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> updateCoupons(@RequestBody CouponsDTO dtoCoupon) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Coupons coupons = couponsConverter.convertCouponsDTOToEntity(dtoCoupon);
            Coupons tempCoupons = couponsService.getCouponByCode(dtoCoupon.getCode());
            if (tempCoupons == null) {
                response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            couponsService.updateCoupon(coupons);
            response.setSuccessCode(SuccessCode.COUPONS_UPDATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> deleteNewCoupons(@PathVariable Long couponID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Coupons tempCoupons = couponsService.getCouponById(couponID);
            if (tempCoupons == null) {
                response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            couponsService.deleteCoupon(couponID);
            response.setSuccessCode(SuccessCode.COUPONS_DELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_COUPONS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> unDeleteNewCoupons(@PathVariable Long couponID) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Coupons tempCoupons = couponsService.getCouponById(couponID);
            if (tempCoupons == null) {
                response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            couponsService.unDeleteCoupon(couponID);
            response.setSuccessCode(SuccessCode.COUPONS_UNDELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getCoupon(@PathVariable Long couponID) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Coupons tempCoupons = couponsService.getCouponById(couponID);
            if (tempCoupons == null) {
                response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            response.setData(tempCoupons);
            response.setSuccessCode(SuccessCode.GET_COUPONS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getListCoupon(@PathVariable Long couponID) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Coupons> list = couponsService.findAllCoupons();
            if (list.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
            response.setData(list);
            response.setSuccessCode(SuccessCode.GET_ALL_COUPONS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody ProductDTO dtoProduct) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Product product = productConverter.convertProductDTOToEntity(dtoProduct);
            boolean checkExistProduct = productService.checkExistProduct(product.getName(),product.getDescription(), product.getBrand().getId());
            if (checkExistProduct) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_EXISTED);
                throw new DuplicateDataException(ErrorCode.ERR_PRODUCT_EXISTED);
            }
            productService.saveProduct(product);
            response.setSuccessCode(SuccessCode.PRODUCT_CREATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO dtoProduct) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Product product = productConverter.convertProductDTOToEntity(dtoProduct);
            boolean checkExistProduct = productService.checkExistProduct(product.getName(),product.getDescription(), product.getBrand().getId());
            if (!checkExistProduct) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            productService.saveProduct(product);
            response.setSuccessCode(SuccessCode.PRODUCT_UPDATE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long productId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            productService.deleteProduct(productId);
            response.setSuccessCode(SuccessCode.PRODUCT_DELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> unDeleteProduct(@PathVariable Long productId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            productService.activeProduct(productId);
            response.setSuccessCode(SuccessCode.PRODUCT_UNDELETE_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getProduct(@PathVariable Long productId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Product product = productService.getProductById(productId);
            if (product == null) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            response.setData(product);
            response.setSuccessCode(SuccessCode.GET_PRODUCT_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getProductList(@PathVariable Long productId) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Product> list = productService.findAllProductForAdmin();
            if (list == null) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            response.setData(list);
            response.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

}
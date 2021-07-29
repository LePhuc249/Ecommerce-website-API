package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;
import nashtech.phucldh.ecommerce.dto.Brand.BrandDTO;
import nashtech.phucldh.ecommerce.dto.Category.CategoryDTO;
import nashtech.phucldh.ecommerce.dto.Coupons.CouponsDTO;
import nashtech.phucldh.ecommerce.dto.Image.ImageDTO;
import nashtech.phucldh.ecommerce.dto.Product.CreateProductDTO;
import nashtech.phucldh.ecommerce.dto.OrganizationAddress.OrganizationAddressDTO;
import nashtech.phucldh.ecommerce.dto.Organization.OrganizationDTO;
import nashtech.phucldh.ecommerce.dto.Product.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.dto.Product.UpdateProductDTO;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.service.AccountOrderService;
import nashtech.phucldh.ecommerce.service.BrandService;
import nashtech.phucldh.ecommerce.service.CategoryService;
import nashtech.phucldh.ecommerce.service.CouponsService;
import nashtech.phucldh.ecommerce.service.ImageService;
import nashtech.phucldh.ecommerce.service.OrganizationAddressService;
import nashtech.phucldh.ecommerce.service.OrganizationService;
import nashtech.phucldh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manager")
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
    private ImageService imageService;

    @Autowired
    private AccountOrderService accountOrderService;

    @PostMapping("/organization")
    public ResponseEntity<ResponseDTO> createNewOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = organizationService.createOrganization(organizationDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
            throw new CreateDataFailException("Manager Rest Controller: " + ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organization")
    public ResponseEntity<ResponseDTO> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = organizationService.updateOrganization(organizationDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organization/lock/{organizationId}")
    public ResponseEntity<ResponseDTO> deactiveOrganization(@PathVariable int organizationId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(organizationId));
            boolean result = organizationService.deleteOrganization(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organization/unlock/{organizationId}")
    public ResponseEntity<ResponseDTO> activeOrganization(@PathVariable int organizationId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(organizationId));
            boolean result = organizationService.activeOrganization(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/organization/details/{organizationId}")
    public ResponseEntity<ResponseDTO> getOrganization(@PathVariable int organizationId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(organizationId));
            OrganizationDTO dto = organizationService.getOrganizationToShowByID(id);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_ORGANIZATION_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/organization/{page}")
    public ResponseEntity<ResponseDTO> getAllOrganization(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<OrganizationDTO> listDTO = organizationService.getOrganizationListToShowByID(pageNo, "name");
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_ORGANIZATION_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/brand")
    public ResponseEntity<ResponseDTO> createNewBrand(@Valid @RequestBody BrandDTO brandDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = brandService.addNewBrand(brandDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.BRAND_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_BRAND_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_BRAND_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/brand")
    public ResponseEntity<ResponseDTO> updateBrand(@Valid @RequestBody BrandDTO brandDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = brandService.updateBrand(brandDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.BRAND_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_BRAND_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_BRAND_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/brand/{brandId}")
    public ResponseEntity<ResponseDTO> deleteBrand(@PathVariable("brandId") int brandId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(brandId));
            boolean result = brandService.deleteBrand(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.BRAND_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_BRAND_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_BRAND_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/brand/details/{brandId}")
    public ResponseEntity<ResponseDTO> getBrand(@PathVariable("brandId") int brandId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(brandId));
            BrandDTO dto = brandService.getBrandToShow(id);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_BRAND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/brand/{page}")
    public ResponseEntity<ResponseDTO> getAllBrand(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<BrandDTO> listDTO = brandService.getBrandListToShow(pageNo, "name");
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_BRAND_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseDTO> createNewCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = categoryService.save(categoryDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = categoryService.updateCategory(categoryDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category/deactive/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable("categoryId") int categoryId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(categoryId));
            boolean result = categoryService.delete(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category/active/{categoryId}")
    public ResponseEntity<ResponseDTO> activeCategory(@PathVariable("categoryId") int categoryId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(categoryId));
            boolean result = categoryService.undelete(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/category/details/{categoryId}")
    public ResponseEntity<ResponseDTO> getCategory(@PathVariable("categoryId") int categoryId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.parseLong(String.valueOf(categoryId));
            CategoryDTO dto = categoryService.getCategoryToShow(id);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_CATEGORY_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/category/{page}")
    public ResponseEntity<ResponseDTO> getAllCategory(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<CategoryDTO> listDTO = categoryService.getListCategoryToShow(pageNo, "name");
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_CATEGORY_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/organizationaddress")
    public ResponseEntity<ResponseDTO> createNewAddressForOrganization(@Valid @RequestBody OrganizationAddressDTO organizationAddressDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = organizationAddressService.createNewAddress(organizationAddressDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organizationaddress")
    public ResponseEntity<ResponseDTO> updateAddressForOrganization(@Valid @RequestBody OrganizationAddressDTO organizationAddressDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = organizationAddressService.updateAddress(organizationAddressDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/organizationaddress/{organizationAddressId}")
    public ResponseEntity<ResponseDTO> deleteAddressForOrganization(@PathVariable("organizationAddressId") int organizationAddressId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(organizationAddressId));
            boolean result = organizationAddressService.deleteAddress(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/organizationaddress/details/{organizationAddressId}")
    public ResponseEntity<ResponseDTO> getAddressForOrganization(@PathVariable("organizationAddressId") int organizationAddressId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(organizationAddressId));
            List<OrganizationAddressDTO> listDTO = organizationAddressService.getOrganizationAddressToShow(id);
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ORGANIZATION_ADDRESS_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/organizationaddress/{page}")
    public ResponseEntity<ResponseDTO> getAllAddressForOrganization(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<OrganizationAddressDTO> listDTO = organizationAddressService.getListAllOrganizationAddressToShow(pageNo, "address");
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_ORGANIZATION_ADDRESS_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/coupons")
    public ResponseEntity<ResponseDTO> createNewCoupons(@Valid @RequestBody CouponsDTO dtoCoupon) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = couponsService.createCoupon(dtoCoupon);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_COUPONS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_COUPONS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/coupons")
    public ResponseEntity<ResponseDTO> updateCoupons(@Valid @RequestBody CouponsDTO dtoCoupon) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = couponsService.updateCoupon(dtoCoupon);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/coupons/delete/{couponID}")
    public ResponseEntity<ResponseDTO> deleteNewCoupons(@PathVariable("couponID") int couponID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(couponID));
            boolean result = couponsService.deleteCoupon(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_COUPONS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_COUPONS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/coupons/undelete/{couponID}")
    public ResponseEntity<ResponseDTO> unDeleteNewCoupons(@PathVariable("couponID") int couponID) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(couponID));
            boolean result = couponsService.unDeleteCoupon(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/coupons/details/{couponID}")
    public ResponseEntity<ResponseDTO> getCoupon(@PathVariable("couponID") int couponID) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(couponID));
            CouponsDTO dto = couponsService.getCouponsToShow(id);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_COUPONS_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/coupons/{page}")
    public ResponseEntity<ResponseDTO> getListCoupon(@PathVariable("page") int pageNo) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try {
            List<CouponsDTO> listDTO = couponsService.getListCouponsToShow(pageNo, "code");
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_COUPONS_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody CreateProductDTO dtoProduct) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = productService.saveProduct(dtoProduct);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody UpdateProductDTO dtoProduct) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = productService.updateProduct(dtoProduct);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/delete/{productId}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable("productId") int productId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(productId));
            boolean result = productService.deleteProduct(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/undelete/{productId}")
    public ResponseEntity<ResponseDTO> unDeleteProduct(@PathVariable("productId") int productId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(productId));
            boolean result = productService.activeProduct(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/product/search")
    public ResponseEntity<ResponseDTO> getProductListByNameOrCategory(String name, int categoryId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long cateId = Long.valueOf(String.valueOf(categoryId));
            List<Product> list = productService.findByNameOrCategory(name, cateId);
            if (list.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            response.setData(list);
            response.setSuccessCode(SuccessCode.PRODUCT_LIST_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/product/details/{productId}")
    public ResponseEntity<ResponseDTO> getProduct(@PathVariable("productId") int productId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(productId));
            ProductDetailDTO dto = productService.getProductDetailByID(id);
            if (dto != null) {
                response.setData(dto);
                response.setSuccessCode(SuccessCode.PRODUCT_LOADED_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_PRODUCT_LOADED_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/product/{page}")
    public ResponseEntity<ResponseDTO> getProductList(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<ProductDetailDTO> listDTO = productService.getListProductForAdmin(pageNo, "name");
            if (listDTO.size() > 0) {
                response.setData(listDTO);
                response.setSuccessCode(SuccessCode.PRODUCT_LIST_LOADED_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_PRODUCT_LIST_EMPTY);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/image")
    public ResponseEntity<ResponseDTO> createNewImage(@RequestBody ImageDTO dto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = imageService.addNewImage(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.IMAGE_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_IMAGE_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_CREATE_IMAGE_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_IMAGE_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/image")
    public ResponseEntity<ResponseDTO> updateImage(@RequestBody ImageDTO dto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = imageService.updateImage(dto);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.IMAGE_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/image/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteImage(@PathVariable("id") int id) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idImage = Long.valueOf(String.valueOf(id));
            boolean result = imageService.deleteImage(idImage);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.IMAGE_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_IMAGE_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_DELETE_IMAGE_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_IMAGE_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/image/undelete/{id}")
    public ResponseEntity<ResponseDTO> unDeleteImage(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idImage = Long.valueOf(String.valueOf(id));
            boolean result = imageService.activeImage(idImage);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.IMAGE_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_IMAGE_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<ResponseDTO> getImage(@PathVariable("id") int id) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idImage = Long.valueOf(String.valueOf(id));
            ImageDTO dto = imageService.getImage(idImage);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.IMAGE_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_IMAGE_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/image")
    public ResponseEntity<ResponseDTO> getAllImage() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<ImageDTO> listDTO = imageService.getAllImage();
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.IMAGE_LIST_LOADED_SUCCESS);
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_IMAGE_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_IMAGE_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accountorder/update/finish/{id}")
    public ResponseEntity<ResponseDTO> updateAccountOrderToFinish(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idOrder = Long.valueOf(String.valueOf(id));
            boolean result = accountOrderService.updateStatusToFinish(idOrder);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accountorder/update/cancel/{id}")
    public ResponseEntity<ResponseDTO> updateAccountOrderToCancel(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idOrder = Long.valueOf(String.valueOf(id));
            boolean result = accountOrderService.updateStatusToCancel(idOrder);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accountorder/update/confirm/{id}")
    public ResponseEntity<ResponseDTO> updateAccountOrderToConfirm(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idOrder = Long.valueOf(String.valueOf(id));
            boolean result = accountOrderService.updateStatusToConfirm(idOrder);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accountorder/update/process/{id}")
    public ResponseEntity<ResponseDTO> updateAccountOrderToProcess(@PathVariable("id") int id) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long idOrder = Long.valueOf(String.valueOf(id));
            boolean result = accountOrderService.updateStatusToProcess(idOrder);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            }
        } catch (Exception e) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ACCOUNT_ORDER_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

}
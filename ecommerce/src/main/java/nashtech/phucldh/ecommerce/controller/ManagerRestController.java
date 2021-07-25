package nashtech.phucldh.ecommerce.controller;

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
import nashtech.phucldh.ecommerce.dto.CreateProductDTO;
import nashtech.phucldh.ecommerce.dto.OrganizationAddressDTO;
import nashtech.phucldh.ecommerce.dto.OrganizationDTO;
import nashtech.phucldh.ecommerce.dto.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.dto.UpdateProductDTO;

import nashtech.phucldh.ecommerce.entity.Brand;
import nashtech.phucldh.ecommerce.entity.Category;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.entity.Organization;
import nashtech.phucldh.ecommerce.entity.OrganizationAddress;
import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.service.BrandService;
import nashtech.phucldh.ecommerce.service.CategoryService;
import nashtech.phucldh.ecommerce.service.CouponsService;
import nashtech.phucldh.ecommerce.service.OrganizationAddressService;
import nashtech.phucldh.ecommerce.service.OrganizationService;
import nashtech.phucldh.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
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
import java.util.ArrayList;
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
    private OrganizationConverter organizationConverter;

    @Autowired
    private BrandConverter brandConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private OrganizationAddressConverter organizationAddressConverter;

    @Autowired
    private CouponsConverter couponsConverter;

    @Autowired
    private ProductConverter productConverter;

    @PostMapping("/organization")
    public ResponseEntity<ResponseDTO> createNewOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = organizationService.createOrganization(organizationDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
            throw new CreateDataFailException("Manager Rest Controller: " + ErrorCode.ERR_CREATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organization")
    public ResponseEntity<ResponseDTO> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = organizationService.updateOrganization(organizationDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organization/lock/{organizationId}")
    public ResponseEntity<ResponseDTO> deactiveOrganization(@PathVariable int organizationId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.parseLong(String.valueOf(organizationId));
            result = organizationService.deleteOrganization(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_ORGANIZATION_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organization/unlock/{organizationId}")
    public ResponseEntity<ResponseDTO> activeOrganization(@PathVariable int organizationId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.parseLong(String.valueOf(organizationId));
            result = organizationService.activeOrganization(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_FAIL);
            }
        } catch (Exception ex) {
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
            Organization organization = organizationService.getOrganizationByID(id);
            if (organization == null) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            }
            OrganizationDTO dto = organizationConverter.convertOrganizationToDTO(organization);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_ORGANIZATION_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/organization/{page}")
    public ResponseEntity<ResponseDTO> getAllOrganization(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Organization> page = organizationService.getPaginationOrganization(pageNo, "name");
            List<OrganizationDTO> listDTO = organizationConverter.toDTOList(page.getContent());
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
            boolean result;
            result = brandService.addNewBrand(brandDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.BRAND_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_BRAND_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_BRAND_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/brand")
    public ResponseEntity<ResponseDTO> updateBrand(@Valid @RequestBody BrandDTO brandDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = brandService.updateBrand(brandDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.BRAND_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_BRAND_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_BRAND_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_BRAND_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/brand/{brandId}")
    public ResponseEntity<ResponseDTO> deleteBrand(@PathVariable("brandId") int brandId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.parseLong(String.valueOf(brandId));
            result = brandService.deleteBrand(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.BRAND_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_BRAND_FAIL);
            }
        } catch (Exception ex) {
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
            Brand brand = brandService.getBrand(id);
            BrandDTO dto = brandConverter.convertBrandToDTO(brand);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_BRAND_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/brand/{page}")
    public ResponseEntity<ResponseDTO> getAllBrand(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Brand> page = brandService.getPaginationBrand(pageNo, "name");
            List<BrandDTO> listDTO = brandConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_BRAND_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_BRAND_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_BRAND_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseDTO> createNewCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = categoryService.save(categoryDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = categoryService.updateCategory(categoryDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category/deactive/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable("categoryId") int categoryId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.parseLong(String.valueOf(categoryId));
            result = categoryService.delete(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/category/active/{categoryId}")
    public ResponseEntity<ResponseDTO> activeCategory(@PathVariable("categoryId") int categoryId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.parseLong(String.valueOf(categoryId));
            result = categoryService.undelete(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CATEGORY_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
            }
        } catch (Exception ex) {
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
            Category category = categoryService.findById(id);
            CategoryDTO dto = categoryConverter.convertCategoryToDto(category);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_CATEGORY_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/category/{page}")
    public ResponseEntity<ResponseDTO> getAllCategory(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Category> page = categoryService.getPaginationCategory(pageNo, "name");
            List<CategoryDTO> listDTO = categoryConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_CATEGORY_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CATEGORY_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/organizationaddress")
    public ResponseEntity<ResponseDTO> createNewAddressForOrganization(@Valid @RequestBody OrganizationAddressDTO organizationAddressDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = organizationAddressService.createNewAddress(organizationAddressDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/organizationaddress")
    public ResponseEntity<ResponseDTO> updateAddressForOrganization(@Valid @RequestBody OrganizationAddressDTO organizationAddressDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = organizationAddressService.updateAddress(organizationAddressDTO);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/organizationaddress/{organizationAddressId}")
    public ResponseEntity<ResponseDTO> deleteAddressForOrganization(@PathVariable("organizationAddressId") int organizationAddressId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.valueOf(String.valueOf(organizationAddressId));
            result = organizationAddressService.deleteAddress(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORGANIZATION_ADDRESS_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_ORGANIZATION_ADDRESS_FAIL);
            }
        } catch (Exception ex) {
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
            List<OrganizationAddress> listAddress = organizationAddressService.getListAddressOfOrganization(id);
            if (listAddress.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            }
            List<OrganizationAddressDTO> listDTO = new ArrayList<>();
            for (OrganizationAddress address : listAddress) {
                OrganizationAddressDTO dto = organizationAddressConverter.convertOrganizationToDTO(address);
                listDTO.add(dto);
            }
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ORGANIZATION_ADDRESS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/organizationaddress/{page}")
    public ResponseEntity<ResponseDTO> getAllAddressForOrganization(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<OrganizationAddress> page = organizationAddressService.getPaginationOrganizationAddress(pageNo, "address");
            List<OrganizationAddressDTO> listDTO = organizationAddressConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_ORGANIZATION_ADDRESS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ORGANIZATION_ADDRESS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/coupons")
    public ResponseEntity<ResponseDTO> createNewCoupons(@Valid @RequestBody CouponsDTO dtoCoupon) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = couponsService.createCoupon(dtoCoupon);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_COUPONS_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_COUPONS_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/coupons")
    public ResponseEntity<ResponseDTO> updateCoupons(@Valid @RequestBody CouponsDTO dtoCoupon) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = couponsService.updateCoupon(dtoCoupon);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/coupons/delete/{couponID}")
    public ResponseEntity<ResponseDTO> deleteNewCoupons(@PathVariable("couponID") int couponID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.valueOf(String.valueOf(couponID));
            result = couponsService.deleteCoupon(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_COUPONS_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_COUPONS_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_COUPONS_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/coupons/undelete/{couponID}")
    public ResponseEntity<ResponseDTO> unDeleteNewCoupons(@PathVariable("couponID") int couponID) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.valueOf(String.valueOf(couponID));
            result = couponsService.unDeleteCoupon(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.COUPONS_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_COUPONS_FAIL);
            }
        } catch (Exception ex) {
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
            Coupons tempCoupons = couponsService.getCouponById(id);
            CouponsDTO dto = couponsConverter.convertCouponsToDTO(tempCoupons);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_COUPONS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/coupons/{page}")
    public ResponseEntity<ResponseDTO> getListCoupon(@PathVariable("page") int pageNo) throws DataNotFoundException {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Coupons> page = couponsService.getPaginationCoupons(pageNo, "code");
            List<CouponsDTO> listDTO = couponsConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_COUPONS_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseDTO> createProduct(@Valid @RequestBody CreateProductDTO dtoProduct) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = productService.saveProduct(dtoProduct);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody UpdateProductDTO dtoProduct) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            result = productService.updateProduct(dtoProduct);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_UPDATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/delete/{productId}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable("productId") int productId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.valueOf(String.valueOf(productId));
            result = productService.deleteProduct(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_DELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/undelete/{productId}")
    public ResponseEntity<ResponseDTO> unDeleteProduct(@PathVariable("productId") int productId) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            Long id = Long.valueOf(String.valueOf(productId));
            result = productService.activeProduct(id);
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.PRODUCT_UNDELETE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/product/search")
    public ResponseEntity<ResponseDTO> getProductListByNameOrCategory(@PathVariable String name, @PathVariable int categoryId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long cateId = Long.valueOf(String.valueOf(categoryId));
            List<Product> list = productService.findByNameOrCategory(name, cateId);
            if (list.size() == 0) {
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

    @GetMapping("/productdetail/{productId}")
    public ResponseEntity<ResponseDTO> getProduct(@PathVariable("productId") int productId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(productId));
            Product product = productService.getProductById(id);
            ProductDetailDTO dto = productConverter.convertProductDetailToDto(product);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_PRODUCT_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/product/{page}")
    public ResponseEntity<ResponseDTO> getProductList(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Product> page = productService.getPaginationProductForAdmin(pageNo, "name");
            List<ProductDetailDTO> listDTO = productConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

}
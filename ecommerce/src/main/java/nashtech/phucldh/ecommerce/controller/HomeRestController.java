package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;

import nashtech.phucldh.ecommerce.converter.AccountConverter;
import nashtech.phucldh.ecommerce.converter.ProductConverter;

import nashtech.phucldh.ecommerce.dto.AccountForgotDTO;
import nashtech.phucldh.ecommerce.dto.AccountProfileDTO;
import nashtech.phucldh.ecommerce.dto.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import nashtech.phucldh.ecommerce.service.AccountService;
import nashtech.phucldh.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/home")
public class HomeRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountConverter accountConverter;

    @GetMapping("/all/{productId}")
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

    @GetMapping("/all/check") // check this again
    public ResponseEntity<ResponseDTO> getProductListByNameOrCategory(@PathVariable String name, @PathVariable int categoryId) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long cateId = Long.valueOf(String.valueOf(categoryId));
            List<Product> list = productService.findByNameOrCategoryForCustomer(name, cateId);
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

    @GetMapping("/all/{page}")
    public ResponseEntity<ResponseDTO> getProductList(@PathVariable("page") int pageNo) {
        ResponseDTO response = new ResponseDTO();
        try {
            Page<Product> page = productService.getPaginationProductForCustomer(pageNo, "name");
            List<ProductDetailDTO> listDTO = productConverter.toDTOList(page.getContent());
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/forgotAccount")
    public ResponseEntity<ResponseDTO> getForgotAccount(@RequestBody AccountForgotDTO accountForgotDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            Account account =
                    accountService.getForgotAccount(accountForgotDTO.getUserName(), accountForgotDTO.getFullName(), accountForgotDTO.getEmail(), accountForgotDTO.getPhone());
            if (account != null) {
                AccountProfileDTO dto = accountConverter.convertAccountProfileToDto(account);
                response.setSuccessCode(SuccessCode.ACCOUNT_LOADED_SUCCESS);
                response.setData(dto);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

}

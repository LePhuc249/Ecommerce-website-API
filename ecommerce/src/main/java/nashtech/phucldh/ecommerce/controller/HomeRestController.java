package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;

import nashtech.phucldh.ecommerce.converter.ProductConverter;

import nashtech.phucldh.ecommerce.dto.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;

import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import nashtech.phucldh.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getProductList() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Product> list = productService.findAllProductForAdmin();
            if (list.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
            List<ProductDetailDTO> listDTO = new ArrayList<>();
            for (Product product : list) {
                ProductDetailDTO dto = productConverter.convertProductDetailToDto(product);
                listDTO.add(dto);
            }
            response.setData(listDTO);
            response.setSuccessCode(SuccessCode.GET_ALL_PRODUCT_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

}

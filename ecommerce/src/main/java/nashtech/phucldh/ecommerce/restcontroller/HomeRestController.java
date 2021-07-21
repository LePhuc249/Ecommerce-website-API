package nashtech.phucldh.ecommerce.restcontroller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.converter.ProductConverter;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.entity.Product;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/home")
public class HomeRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;


    public ResponseEntity<ResponseDTO> getListAllProductForCustomer() {
        ResponseDTO response = new ResponseDTO();
        try {

        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ResponseDTO> getListProductForCustomerByNameOrCategory(@PathVariable String name, @PathVariable Long categoryId) {
        ResponseDTO response = new ResponseDTO();
        try {
            List<Product> listProduct = productService.findByNameOrCategoryForCustomer(name, categoryId);
            if (listProduct.size() == 0) {
                response.setErrorCode(ErrorCode.ERR_PRODUCT_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND);
            }
        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

}

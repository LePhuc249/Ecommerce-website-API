package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;

import nashtech.phucldh.ecommerce.converter.ProductConverter;
import nashtech.phucldh.ecommerce.dto.AddCartItemDTO;
import nashtech.phucldh.ecommerce.dto.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.dto.UpdateCartItemDTO;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.entity.CartItem;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import nashtech.phucldh.ecommerce.service.AccountService;
import nashtech.phucldh.ecommerce.service.CartItemService;
import nashtech.phucldh.ecommerce.service.CartService;
import nashtech.phucldh.ecommerce.service.CouponsService;
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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CouponsService couponsService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductConverter productConverter;

    @GetMapping("/alldetail/{productId}")
    public ResponseEntity<ResponseDTO> getProduct(@PathVariable int productId) {
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

    @GetMapping("/all/") // check this
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

    @GetMapping("/coupon/{code}")
    public ResponseEntity<ResponseDTO> checkCouponCode(@PathVariable String code) {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result = false;
            Coupons coupon = couponsService.getCouponCanUseByCode(code);
            if (coupon != null) {
                result = true;
            }
            if (result) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.GET_COUPONS_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_COUPONS_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_COUPONS_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addItemToCart(@RequestBody AddCartItemDTO dto) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long accountID = dto.getUserId();
            Account account = accountService.getAccountDetail(accountID);
            Cart cart = cartService.getCartOfCustomer(account);
            List<CartItem> list;
            if (cart != null) {
                list = cart.getListItem();
                CartItem cartItem = cartItemService.checkCartItem(cart.getId(), dto.getItemId());
                if (list.size() == 0 && cartItem == null) {
                    list.add(cartItem);
                } else if (list.size() != 0 && cartItem == null) {
                    list.add(cartItem);
                } else if (list.size() != 0 && cartItem != null) {
                    cartItemService.updateQuantityItemInCart(cartItem.getId(), cartItem.getAmount() + 1);
                }
            } else {
                Cart newCart = new Cart();
                newCart.setAccount(account);
                list = new ArrayList<CartItem>();
                Product product = productService.getProductById(dto.getItemId());
                CartItem item = new CartItem();
                item.setCart(newCart);
                item.setProduct(product);
                item.setPrice(product.getPrice());
                item.setAmount(1);
                list.add(item);
                cart.setListItem(list);
            }
        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateCart(@RequestBody UpdateCartItemDTO dto) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long accountID = dto.getUserId();
            Account account = accountService.getAccountDetail(accountID);
            Cart cart = cartService.getCartOfCustomer(account);
            if (cart != null) {
                List<CartItem> list = cart.getListItem();
                CartItem cartItem = cartItemService.checkCartItem(cart.getId(), dto.getItemId());
            } else {
                throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
            }
        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/removeItem")
    public ResponseEntity<ResponseDTO> removeItemToCart() {
        ResponseDTO response = new ResponseDTO();
        try {

        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> clearCart() {
        ResponseDTO response = new ResponseDTO();
        try {

        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getCartOfCustomer() {
        ResponseDTO response = new ResponseDTO();
        try {

        } catch (Exception ex) {

        }
        return ResponseEntity.ok().body(response);
    }

}
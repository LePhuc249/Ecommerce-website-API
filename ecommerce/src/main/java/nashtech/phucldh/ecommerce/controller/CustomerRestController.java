package nashtech.phucldh.ecommerce.controller;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.constants.SuccessCode;

import nashtech.phucldh.ecommerce.converter.CartConverter;
import nashtech.phucldh.ecommerce.converter.ProductConverter;
import nashtech.phucldh.ecommerce.dto.AccountOrderDTO;
import nashtech.phucldh.ecommerce.dto.AddCartItemDTO;
import nashtech.phucldh.ecommerce.dto.CartDTO;
import nashtech.phucldh.ecommerce.dto.ProductDetailDTO;
import nashtech.phucldh.ecommerce.dto.ResponseDTO;
import nashtech.phucldh.ecommerce.dto.UpdateCartItemDTO;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.AccountOrder;
import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.entity.CartItem;
import nashtech.phucldh.ecommerce.entity.Coupons;
import nashtech.phucldh.ecommerce.entity.OrderDetail;
import nashtech.phucldh.ecommerce.entity.Product;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;

import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.service.AccountOrderService;
import nashtech.phucldh.ecommerce.service.AccountService;
import nashtech.phucldh.ecommerce.service.CartItemService;
import nashtech.phucldh.ecommerce.service.CartService;
import nashtech.phucldh.ecommerce.service.CouponsService;
import nashtech.phucldh.ecommerce.service.OrderDetailService;
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

import java.time.LocalDateTime;
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

    @Autowired
    private CartConverter cartConverter;

    @Autowired
    private AccountOrderService accountOrderService;

    @Autowired
    private OrderDetailService orderDetailService;

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

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addItemToCart(@RequestBody AddCartItemDTO dto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long accountID = dto.getUserId();
            Account account = accountService.getAccountDetail(accountID);
            Cart cart = cartService.getCartOfCustomer(account);
            if (cart != null) {
                CartItem item = new CartItem();
                Product product = productService.getProductById(dto.getItemId());
                item.setCart(cart);
                item.setProduct(product);
                item.setPrice(product.getPrice());
                item.setAmount(1);
                boolean addToCart = cartItemService.createNewItemInCart(item);
                if (addToCart) {
                    response.setData(true);
                    response.setSuccessCode(SuccessCode.CART_CREATE_SUCCESS);
                } else {
                    response.setErrorCode(ErrorCode.ERR_ADD_ITEM_CART_FAIL);
                    throw new CreateDataFailException(ErrorCode.ERR_ADD_ITEM_CART_FAIL);
                }
            } else {
                Cart newCart = new Cart();
                newCart.setAccount(account);
                newCart.setCreateDate(LocalDateTime.now());
                cartService.createCart(newCart);
                Product product = productService.getProductById(dto.getItemId());
                CartItem item = new CartItem();
                Cart cartCreated = cartService.getCartOfCustomer(account);
                item.setCart(cartCreated);
                item.setProduct(product);
                item.setPrice(product.getPrice());
                item.setAmount(1);
                boolean addToCart = cartItemService.createNewItemInCart(item);
                if (addToCart) {
                    response.setData(true);
                    response.setSuccessCode(SuccessCode.CART_CREATE_SUCCESS);
                } else {
                    response.setErrorCode(ErrorCode.ERR_ADD_ITEM_CART_FAIL);
                    throw new CreateDataFailException(ErrorCode.ERR_ADD_ITEM_CART_FAIL);
                }
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_CART_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/updateToCart")
    public ResponseEntity<ResponseDTO> updateCart(@RequestBody UpdateCartItemDTO dto) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long accountID = dto.getUserId();
            Account account = accountService.getAccountDetail(accountID);
            Cart cart = cartService.getCartOfCustomer(account);
            if (cart != null) {
                CartItem cartItem = cartItemService.checkCartItem(cart.getId(), dto.getItemId());
                if (cartItem == null) {
                    response.setErrorCode(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
                    throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
                } else {
                    cartItem.setAmount(dto.getAmount());
                    boolean checkUpdate = cartItemService.updateNewItemInCart(cartItem);
                    if (checkUpdate) {
                        response.setSuccessCode(SuccessCode.CART_UPDATE_SUCCESS);
                        response.setData(true);
                    } else {
                        response.setErrorCode(ErrorCode.ERR_UPDATE_ITEM_CART_FAIL);
                        throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_ITEM_CART_FAIL);
                    }
                }
            } else {
                response.setErrorCode(ErrorCode.ERR_CART_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_UPDATE_CART_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/removeItem/{itemId}")
    public ResponseEntity<ResponseDTO> removeItemToCart(@PathVariable("itemId") int itemId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(itemId));
            boolean resultDelete = cartItemService.deleteCartItem(id);
            if (resultDelete) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CART_DELETE_SUCCESS);
            } else {
                response.setErrorCode(ErrorCode.ERR_REMOVE_ITEM_CART_FAIL);
                throw new DeleteDataFailException(ErrorCode.ERR_REMOVE_ITEM_CART_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_CART_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/clearCart/{accountID}")
    public ResponseEntity<ResponseDTO> clearCart(@PathVariable("accountID") int accountID) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(accountID));
            Account account = accountService.getAccountDetail(id);
            Cart cart = cartService.getCartOfCustomer(account);
            boolean result1 = cartItemService.deleteAllItemInCart(cart.getId());
            boolean result2 = cartService.deleteCart(cart.getId());
            if (result1 && result2) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.CART_CLEAR_SUCCESS);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_DELETE_CART_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CART_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/cart/account/{accountID}")
    public ResponseEntity<ResponseDTO> getCartOfCustomer(@PathVariable("accountID") int accountID) {
        ResponseDTO response = new ResponseDTO();
        try {
            Long id = Long.valueOf(String.valueOf(accountID));
            Account account = accountService.getAccountDetail(id);
            Cart cart = cartService.getCartOfCustomer(account);
            List<CartItem> listItem = cartItemService.getListItemOfCart(cart.getId());
            cart.setListItem(listItem);
            CartDTO dto = cartConverter.convertCartToDto(cart);
            response.setData(dto);
            response.setSuccessCode(SuccessCode.GET_CART_SUCCESS);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CART_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/order")
    public ResponseEntity<ResponseDTO> createOrderAccount(@RequestBody AccountOrderDTO dto) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            boolean result;
            dto.setCreateDate(LocalDateTime.now());
            dto.setDateDelivery(LocalDateTime.now().plusDays(3).toString().substring(0, 10));
            dto.setStatus(Long.valueOf(1));
            Account account = accountService.getAccountDetail(dto.getAccountId());
            Cart cart = cartService.getCartOfCustomer(account);
            List<CartItem> listCartItem = cartItemService.getListItemOfCart(cart.getId());
            int totalPrice = 0;
            for (CartItem cartItem : listCartItem) {
                totalPrice += cartItem.getPrice() * cartItem.getAmount();
            }
            dto.setTotalPrice(Float.parseFloat(String.valueOf(totalPrice)));
            result = accountOrderService.createNewOrder(dto);
            AccountOrder accountOrder = accountOrderService.getAccountOrder(account.getId(), totalPrice);
            if (accountOrder != null) {
                List<OrderDetail> listDetailOfOrder = new ArrayList<>();
                for (CartItem cartItem : listCartItem) {
                    OrderDetail detail = new OrderDetail();
                    detail.setAccountOrder(accountOrder);
                    detail.setCreateDate(LocalDateTime.now());
                    detail.setItemId(cartItem.getProduct().getId());
                    detail.setAmount(cartItem.getAmount());
                    detail.setPrice(cartItem.getPrice());
                    detail.setItemProperty(cartItem.getProduct().getName());
                    listDetailOfOrder.add(detail);
                }
                for (OrderDetail orderDetail : listDetailOfOrder) {
                    orderDetailService.createOrderdetail(orderDetail);
                }
            } else {
                response.setErrorCode(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
                throw new DataNotFoundException(ErrorCode.ERR_ACCOUNT_ORDER_NOT_FOUND);
            }
            boolean result1 = cartItemService.deleteAllItemInCart(cart.getId());
            boolean result2 = cartService.deleteCart(cart.getId());
            if (result & result1 & result2) {
                response.setData(true);
                response.setSuccessCode(SuccessCode.ORDER_CREATE_SUCCESS);
            } else {
                response.setData(false);
                response.setErrorCode(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL);
            }
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL);
        }
        return ResponseEntity.ok().body(response);
    }

}
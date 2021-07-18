package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.CartRepository;
import nashtech.phucldh.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart getCartOfCustomer(Account account) throws DataNotFoundException {
        Cart cart = null;
        Optional<Cart> cartOptional = cartRepository.findByAccount(account);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        return cart;
    }

    @Override
    public void createCart(Cart cart) throws CreateDataFailException {
        try {
            cartRepository.save(cart);
        } catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL); // add cart error
        }
    }

    @Override
    public void deleteCart(Long cartID) throws DataNotFoundException, DeleteDataFailException {
        Cart cart = null;
        Optional<Cart> cartOptional = cartRepository.findById(cartID);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        try {
            cartRepository.deleteById(cartID);
        } catch (Exception ex) {
            throw new DeleteDataFailException(ErrorCode.ERR_CREATE_ACCOUNT_ORDER_FAIL); // add cart error
        }
    }
}

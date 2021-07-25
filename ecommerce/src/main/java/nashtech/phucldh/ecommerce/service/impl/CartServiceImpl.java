package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Cart;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;

import nashtech.phucldh.ecommerce.repository.CartRepository;

import nashtech.phucldh.ecommerce.service.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart getCartOfCustomer(Account account) throws DataNotFoundException {
        Cart cart;
        Optional<Cart> cartOptional = cartRepository.findByAccount(account);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        } else {
            LOGGER.info("Can't find cart of customer: " + account.getUserName());
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        return cart;
    }

    @Override
    public Boolean createCart(Cart cart) throws CreateDataFailException {
        boolean result;
        try {
            cart.setCreateDate(LocalDateTime.now());
            cartRepository.save(cart);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't create cart ");
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CART_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteCart(Long cartID) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        Cart cart = null;
        Optional<Cart> cartOptional = cartRepository.findById(cartID);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
        } else {
            LOGGER.info("Can't find cart with id " + cartID);
            throw new DataNotFoundException(ErrorCode.ERR_CART_NOT_FOUND);
        }
        try {
            cartRepository.deleteById(cartID);
            result = true;
        } catch (Exception ex) {
            LOGGER.info("Can't delete cart " + cartID);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CART_FAIL);
        }
        return result;
    }

}
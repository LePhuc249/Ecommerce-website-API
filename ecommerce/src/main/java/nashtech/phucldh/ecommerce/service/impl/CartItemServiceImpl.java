package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.entity.CartItem;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import nashtech.phucldh.ecommerce.repository.CartItemRepository;

import nashtech.phucldh.ecommerce.service.CartItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAllItem() throws DataNotFoundException {
        List<CartItem> cartItem;
        try {
            cartItem = cartItemRepository.findAll();
        } catch (Exception e) {
            LOGGER.info("Can't find all item cart ");
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getListItemOfCart(Long cartID) throws DataNotFoundException {
        List<CartItem> cartItem;
        try {
            cartItem = cartItemRepository.getListItem(cartID);
        } catch (Exception e) {
            LOGGER.info("Can't find item in cart with id: " + cartID);
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        return cartItem;
    }

    @Override
    public CartItem checkCartItem(Long cartID, Long productID) throws DataNotFoundException {
        CartItem item;
        try {
            item = cartItemRepository.getByCartAndProduct(cartID, productID);
        } catch (Exception e) {
            LOGGER.info("Can't find item in cart ");
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        return item;
    }

    @Override
    public Boolean createNewItemInCart(CartItem cartItem) throws CreateDataFailException {
        boolean result;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItem.getId());
        if (cartItemOptional.isPresent()) {
            LOGGER.info("Item have been in cart ");
            throw new DataNotFoundException(ErrorCode.ERR_DUPLICATE_ITEM_CART);
        }
        try {
            cartItemRepository.save(cartItem);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't create new item in cart id " + cartItem.getCart().getId());
            throw new CreateDataFailException(ErrorCode.ERR_ADD_ITEM_CART_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateNewItemInCart(CartItem cartItem) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        CartItem tempCart = null;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItem.getId());
        if (cartItemOptional.isPresent()) {
            tempCart = cartItemOptional.get();
        } else {
            LOGGER.info("Can't find cart item with id " + cartItem.getId());
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            cartItemRepository.save(cartItem);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't update item in cart with cart item id " + cartItem.getId());
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CART_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteCartItem(Long cartItemID) throws DataNotFoundException, DeleteDataFailException {
        boolean result;
        CartItem cart = null;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemID);
        if (cartItemOptional.isPresent()) {
            cart = cartItemOptional.get();
        } else {
            LOGGER.info("Can't find cart item with id " + cartItemID);
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            cartItemRepository.deleteById(cartItemID);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't update item in cart with cart item id " + cartItemID);
            throw new DeleteDataFailException(ErrorCode.ERR_REMOVE_ITEM_CART_FAIL);
        }
        return result;
    }

    @Override
    public Boolean updateQuantityItemInCart(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException {
        boolean result;
        CartItem cart = null;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isPresent()) {
            cart = cartItemOptional.get();
        } else {
            LOGGER.info("Can't find cart item with id " + id);
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            cartItemRepository.updateItemQuantity(id, quantity);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't update item quantity in cart with cart item id " + id);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CART_FAIL);
        }
        return result;
    }

}
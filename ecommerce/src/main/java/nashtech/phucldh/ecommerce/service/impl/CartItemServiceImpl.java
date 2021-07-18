package nashtech.phucldh.ecommerce.service.impl;

import nashtech.phucldh.ecommerce.constants.ErrorCode;
import nashtech.phucldh.ecommerce.entity.CartItem;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import nashtech.phucldh.ecommerce.reponsitory.CartItemRepository;
import nashtech.phucldh.ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAllItem() throws DataNotFoundException {
        List<CartItem> cartItem = null;
        try {
            cartItem = cartItemRepository.findAll();
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> getListItemOfCart(Long cartID) throws DataNotFoundException {
        List<CartItem> cartItem = null;
        try {
            cartItem = cartItemRepository.getListItem(cartID);
        } catch (Exception e) {
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        return cartItem;
    }

    @Override
    public void createNewItemInCart(CartItem cartItem) throws CreateDataFailException {
        try {
            cartItemRepository.save(cartItem);
        } catch (Exception e) {
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_ORDER_DETAIL_FAIL);
        }
    }

    @Override
    public void updateNewItemInCart(CartItem cartItem) throws DataNotFoundException, UpdateDataFailException {
        CartItem tempCart = null;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItem.getId());
        if (cartItemOptional.isPresent()) {
            tempCart = cartItemOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            cartItemRepository.save(cartItem);
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_CREATE_ORDER_DETAIL_FAIL);
        }
    }

    @Override
    public void deleteCartItem(Long cartItemID) throws DataNotFoundException, DeleteDataFailException {
        CartItem cart = null;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemID);
        if (cartItemOptional.isPresent()) {
            cart = cartItemOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            cartItemRepository.deleteById(cartItemID);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERR_CREATE_ORDER_DETAIL_FAIL);
        }
    }

    @Override
    public void updateQuantityItemInCart(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException {
        CartItem cart = null;
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if (cartItemOptional.isPresent()) {
            cart = cartItemOptional.get();
        } else {
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            cartItemRepository.updateItemQuantity(id,quantity);
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_CREATE_ORDER_DETAIL_FAIL);
        }
    }
}

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

@Service
public class CartItemServiceImpl implements CartItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    CartItemRepository cartItemRepository;

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
        CartItem cartItemCheck = cartItemRepository.getByCartAndProduct(cartItem.getCart().getId(), cartItem.getProduct().getId());
        if (cartItemCheck != null) {
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
        CartItem cartItemCheck = cartItemRepository.getByCartAndProduct(cartItem.getCart().getId(), cartItem.getProduct().getId());
        if (cartItemCheck == null) {
            LOGGER.info("Item can't found in cart ");
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
    public Boolean deleteCartItem(Long itemID) throws DataNotFoundException, DeleteDataFailException {
        boolean result = false;
        CartItem cartItem = cartItemRepository.getByItemId(itemID);
        CartItem cartItemCheck = cartItemRepository.getByCartAndProduct(cartItem.getCart().getId(), cartItem.getProduct().getId());
        if (cartItemCheck == null) {
            LOGGER.info("Item can't found in cart ");
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_CART_NOT_FOUND);
        }
        try {
            int resultDelete = cartItemRepository.deleteByItemId(itemID);
            if (resultDelete > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOGGER.info("Can't update item in cart with item id " + itemID);
            throw new DeleteDataFailException(ErrorCode.ERR_REMOVE_ITEM_CART_FAIL);
        }
        return result;
    }

    @Override
    public Boolean deleteAllItemInCart(Long cartId) throws DeleteDataFailException {
        boolean result;
        try {
            cartItemRepository.deleteByCartId(cartId);
            result = true;
        } catch (Exception e) {
            LOGGER.info("Can't update item quantity in cart with cart id " + cartId);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CART_FAIL);
        }
        return result;
    }

}
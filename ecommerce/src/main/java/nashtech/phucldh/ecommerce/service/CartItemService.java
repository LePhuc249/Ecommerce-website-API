package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.CartItem;

import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;

import java.util.List;

public interface CartItemService {

    public List<CartItem> findAllItem() throws DataNotFoundException;

    public List<CartItem> getListItemOfCart(Long cartID) throws DataNotFoundException;

    public CartItem checkCartItem(Long cartID, Long productID) throws DataNotFoundException;

    public Boolean createNewItemInCart(CartItem cartItem) throws CreateDataFailException;

    public Boolean updateNewItemInCart(CartItem cartItem) throws DataNotFoundException, UpdateDataFailException;

    public Boolean deleteCartItem(Long cartItemID) throws DataNotFoundException, DeleteDataFailException;

    public Boolean updateQuantityItemInCart(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException;

}
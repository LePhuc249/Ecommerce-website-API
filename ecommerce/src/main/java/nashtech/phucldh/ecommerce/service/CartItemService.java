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

    public void createNewItemInCart(CartItem cartItem) throws CreateDataFailException;

    public void updateNewItemInCart(CartItem cartItem) throws DataNotFoundException, UpdateDataFailException;

    public void deleteCartItem(Long cartItemID) throws DataNotFoundException, DeleteDataFailException;

    public void updateQuantityItemInCart(Long id, int quantity) throws DataNotFoundException, UpdateDataFailException;

}
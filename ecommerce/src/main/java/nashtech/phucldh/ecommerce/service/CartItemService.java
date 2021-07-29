package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.CartItem;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;
import nashtech.phucldh.ecommerce.exception.UpdateDataFailException;
import java.util.List;

public interface CartItemService {

    List<CartItem> getListItemOfCart(Long cartID) throws DataNotFoundException;

    CartItem checkCartItem(Long cartID, Long productID) throws DataNotFoundException;

    Boolean createNewItemInCart(CartItem cartItem) throws CreateDataFailException;

    Boolean updateNewItemInCart(CartItem cartItem) throws DataNotFoundException, UpdateDataFailException;

    Boolean deleteCartItem(Long cartItemID) throws DataNotFoundException, DeleteDataFailException;

    Boolean deleteAllItemInCart(Long cartId) throws DeleteDataFailException;

}
package nashtech.phucldh.ecommerce.service;

import nashtech.phucldh.ecommerce.entity.Account;
import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.exception.CreateDataFailException;
import nashtech.phucldh.ecommerce.exception.DataNotFoundException;
import nashtech.phucldh.ecommerce.exception.DeleteDataFailException;

public interface CartService {

    public Cart getCartOfCustomer(Account account) throws DataNotFoundException;

    public void createCart(Cart cart) throws CreateDataFailException;

    public void deleteCart(Long cartID) throws DataNotFoundException, DeleteDataFailException;

}
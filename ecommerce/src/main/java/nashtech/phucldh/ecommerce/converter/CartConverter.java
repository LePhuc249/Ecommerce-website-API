package nashtech.phucldh.ecommerce.converter;

import nashtech.phucldh.ecommerce.constants.ErrorCode;

import nashtech.phucldh.ecommerce.dto.CartDTO;
import nashtech.phucldh.ecommerce.dto.CartItemDTO;

import nashtech.phucldh.ecommerce.entity.Cart;
import nashtech.phucldh.ecommerce.entity.CartItem;

import nashtech.phucldh.ecommerce.exception.ConvertEntityDTOException;

import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartConverter.class);

    @Autowired
    private ModelMapper modelMapper;

    public CartDTO convertCartToDto(Cart cart) throws ConvertEntityDTOException {
        try {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setCustomerId(cart.getAccount().getId());
            List<CartItemDTO> cartItems = cart.getListItem().stream().map(this::convertCartItemToDto).collect(Collectors.toList());
            cartDTO.setCartItems(cartItems);
            return cartDTO;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert Cart to CartDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

    public CartItemDTO convertCartItemToDto(CartItem cartItem) throws ConvertEntityDTOException {
        try {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItem.getId());
            cartItemDTO.setItemId(cartItem.getProduct().getId());
            cartItemDTO.setPrice(cartItem.getPrice());
            cartItemDTO.setAmount(cartItem.getAmount());
            return cartItemDTO;
        } catch (Exception ex) {
            LOGGER.info("Fail to convert CartItem to CartItemDTO");
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERTER_DTO_ENTITY_FAIL);
        }
    }

}
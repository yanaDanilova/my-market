package de.danilova.mymarket.Dtos;


import de.danilova.mymarket.utils.Cart;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartDto {
    private List<OrderItemsDto> cartListItems;
    private BigDecimal sum;

    public CartDto(Cart cart){
        this.cartListItems = cart.getCartListItems().stream().map(OrderItemsDto::new).collect(Collectors.toList());
        this.sum = cart.getSum();
    }
}

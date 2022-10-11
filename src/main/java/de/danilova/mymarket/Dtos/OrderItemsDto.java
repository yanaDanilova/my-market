package de.danilova.mymarket.Dtos;

import de.danilova.mymarket.models.OrderItems;
import de.danilova.mymarket.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemsDto {

    private Long orderItemsId;
    private String productTitle;
    private Long productId;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemsDto(OrderItems orderItems){
        this.orderItemsId = orderItems.getId();
        this.productTitle = orderItems.getProduct().getTitle();
        this.productId = orderItems.getProduct().getId();
        this.pricePerProduct = orderItems.getPricePerProduct();
        this.quantity = orderItems.getQuantity();
        this.price = orderItems.getPrice();
    }

}

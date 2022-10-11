package de.danilova.mymarket.utils;

import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.OrderItems;
import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.services.OrderItemsService;
import de.danilova.mymarket.services.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Cart {
    private List<OrderItems> cartListItems;
    private final ProductService productService;
    private final OrderItemsService orderItemsService;
    private BigDecimal sum;


    @PostConstruct
    public void init(){
        cartListItems = new ArrayList<>();

    }


    public void addProductToCart(Long id){
        if(!cartListItems.isEmpty()){
            for(OrderItems orderItems: cartListItems){
                if(orderItems.getProduct().getId().equals(id)){
                    orderItems.increment();
                    orderItemsService.updateOrderItems(orderItems);
                    updateCart();
                    return;
                }
        }

       }
        Product product = productService.getProductById(id).orElseThrow(()->new ResourceNotFoundException("Product does not found"));
        orderItemsService.addOrderItems(new OrderItems(product));
        updateCart();


    }

    private void updateCart() {
        cartListItems.clear();
        cartListItems.addAll(orderItemsService.getAllOrderItems());
        System.out.println(cartListItems.size());
        calculate();
    }


    public void clearCartList() {
        orderItemsService.deleteAllOrderItems();
        cartListItems.clear();
        sum = BigDecimal.ZERO;
    }

    public void deleteProductFromCartById(Long id) {
        for (OrderItems orderItems : cartListItems) {
            if (orderItems.getId().equals(id)) {
                if(orderItems.getQuantity()<1){
                    orderItemsService.deleteOrderItemsById(id);
                    updateCart();
                    return;
                }
                orderItems.decrement();
                orderItemsService.updateOrderItems(orderItems);
                updateCart();
                return;
            }
        }
    }

    public void calculate(){
       sum = BigDecimal.ZERO;
       for (OrderItems orderItems: cartListItems){
           sum = sum.add(orderItems.getPrice());
       }
    }

    public List<OrderItems> getAllOrderItems(){
        return cartListItems;
    }



}

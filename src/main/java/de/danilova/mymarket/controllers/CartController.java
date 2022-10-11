package de.danilova.mymarket.controllers;

import de.danilova.mymarket.Dtos.CartDto;
import de.danilova.mymarket.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {




    private final Cart cart;

    @GetMapping("/add")
    public void addToCart(@RequestParam Long id){
        cart.addProductToCart(id);
    }
    @GetMapping
    public CartDto getCart(){
        return new CartDto(cart);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clearCartList();
    }

    @GetMapping("/deleteById")
    public void deleteProductFromCart(@RequestParam Long id){
        cart.deleteProductFromCartById(id);
    }
}

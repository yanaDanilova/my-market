package de.danilova.mymarket.controllers;

import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.Cart;
import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartController {

    private final Cart cart;

    private final ProductService productService;

    @GetMapping("/add")
    public void addProductToCart(@RequestParam Long id){
        cart.addProductToCart(productService.getProductById(id).orElseThrow(()->new ResourceNotFoundException("Product does not found")));
    }
    @GetMapping
    public List<Product> getCartList(){
        return cart.getCartList();
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

package de.danilova.mymarket.controllers;

import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final  ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
       return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id).orElseThrow();
    }

    @PostMapping
    public Product addNewProduct(Product product){
        return productService.addNewProduct(product);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @PutMapping
    public Product updateProduct(Product product){
      return  productService.updateProductById(product);
    }

}

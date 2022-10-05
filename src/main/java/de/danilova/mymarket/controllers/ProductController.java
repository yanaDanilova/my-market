package de.danilova.mymarket.controllers;

import de.danilova.mymarket.error_handling.MarketError;
import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final  ProductService productService;



    @GetMapping
    public Page<Product> getProducts(
            @RequestParam(name = "min_price",required = false) Long minPrice,
            @RequestParam(name = "max_price", required = false) Long maxPrice,
            @RequestParam(name = "title_part",required = false) String titlePart,
            @RequestParam(name="pageIndex", defaultValue = "1") Integer pageIndex
    ) {
        if(pageIndex<1){
            pageIndex = 1;
        }

       return productService.getProducts(minPrice,maxPrice,titlePart,pageIndex);
    }
    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id).orElseThrow(()->(new ResourceNotFoundException("Product with id: " + id +" is not exist")));
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody @NotNull Product product){
        List<String> errors = new ArrayList<>();
        if (product.getTitle() ==null) {
            errors.add("Too short title");
        }
        if (product.getPrice() ==null) {
            errors.add("Invalid product price");
        }
        if (errors.size() > 0) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product out = productService.addNewProduct(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public void deleteProductById(@RequestParam Long id){
        productService.deleteProductById(id);
    }

    @PutMapping
    public Product updateProduct(Product product){
      return  productService.updateProductById(product);
    }

}

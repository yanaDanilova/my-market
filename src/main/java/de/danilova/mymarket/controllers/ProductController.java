package de.danilova.mymarket.controllers;

import de.danilova.mymarket.Dtos.ProductDto;
import de.danilova.mymarket.error_handling.InvalidDataException;
import de.danilova.mymarket.error_handling.MarketError;
import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final  ProductService productService;



    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name="p", defaultValue = "1") int pageIndex,
            @RequestParam(name = "title_part",required = false) String titlePart,
            @RequestParam(name = "min_price",required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice

    ) {
        if(pageIndex<1){
            pageIndex = 1;
        }
       Page<Product> productPage = productService.getProducts(minPrice,maxPrice,titlePart,pageIndex);
        Page <ProductDto> productDtoPage = new PageImpl<>(productPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), productPage.getPageable(), productPage.getTotalElements());
       return productDtoPage;
    }
    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product =  productService.getProductById(id).orElseThrow(()->(new ResourceNotFoundException("Product with id: " + id +" is not exist")));
        return new ProductDto(product);
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        ProductDto outDto = productService.addNewProduct(productDto);
        return new ResponseEntity<>(outDto, HttpStatus.CREATED);
    }

    @DeleteMapping()
    public void deleteProductById(@RequestParam Long id){
        productService.deleteProductById(id);
    }

    @PutMapping
    public ProductDto updateProduct(ProductDto productDto){
      return  productService.updateProductById(productDto);
    }

}

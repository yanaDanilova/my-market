package de.danilova.mymarket.services;

import de.danilova.mymarket.Dtos.ProductDto;
import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.repositories.ProductRepository;
import de.danilova.mymarket.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> getProducts(BigDecimal minPrice, BigDecimal maxPrice, String titlePart, Integer pageIndex){
        Specification<Product> specification = Specification.where(null);
        if(minPrice != null){
          specification = specification.and(ProductSpecification.priceGreaterOrEqualsThen(minPrice));
        }
        if(maxPrice!=null){
           specification = specification.and(ProductSpecification.priceLesserOrEqualsThen(maxPrice));
        }
        if(titlePart!=null){
          specification =  specification.and(ProductSpecification.titleLike(titlePart));
        }

        return productRepository.findAll(specification, PageRequest.of(pageIndex-1,5));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    @Transactional
    public ProductDto addNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists")));
        productRepository.save(product);
        return new ProductDto(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductDto updateProductById(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("This Product doesn't exists"));
        product.setTitle(product.getTitle());
        product.setPrice(productDto.getPrice());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists")));
        productRepository.save(product);
        return new ProductDto(product);
    }


}

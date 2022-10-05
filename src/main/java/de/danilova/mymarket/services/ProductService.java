package de.danilova.mymarket.services;

import de.danilova.mymarket.models.Product;
import de.danilova.mymarket.repositories.ProductRepository;
import de.danilova.mymarket.specifications.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> getProducts(Long minPrice, Long maxPrice, String titlePart, Integer pageIndex){
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

        return productRepository.findAll(specification, PageRequest.of(pageIndex-1,20));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product addNewProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public Product updateProductById(Product product){
        return productRepository.save(product);
    }


}

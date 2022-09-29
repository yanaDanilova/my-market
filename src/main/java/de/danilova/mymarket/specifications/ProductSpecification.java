package de.danilova.mymarket.specifications;

import de.danilova.mymarket.models.Product;
import org.springframework.data.jpa.domain.Specification;



public class ProductSpecification {
    public static Specification<Product> priceGreaterOrEqualsThen(Long minPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice));
    }

    public static Specification<Product> priceLesserOrEqualsThen(Long maxPrice){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),maxPrice));
    }

    public static Specification<Product> titleLike(String partTitle){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",partTitle)));
    }
}

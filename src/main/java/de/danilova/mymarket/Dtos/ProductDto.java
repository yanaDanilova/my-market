package de.danilova.mymarket.Dtos;


import de.danilova.mymarket.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String title;

    private BigDecimal price;

    private String categoryTitle;

    public ProductDto(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
    }
}

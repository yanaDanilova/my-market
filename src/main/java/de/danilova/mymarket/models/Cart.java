package de.danilova.mymarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private List<Product> cartList;

    @PostConstruct
    public void init(){
        cartList = new ArrayList<>();

    }

    public boolean addProductToCart(Product product){
        return cartList.add(product);
    }

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(cartList);
    }

    public void clearCartList() {
        cartList.clear();
    }

    public boolean deleteProductFromCartById(Long id){
        return cartList.removeIf(product -> (product.getId().equals(id)));
    }

}

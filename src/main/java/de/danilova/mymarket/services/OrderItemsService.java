package de.danilova.mymarket.services;

import de.danilova.mymarket.error_handling.ResourceNotFoundException;
import de.danilova.mymarket.models.OrderItems;
import de.danilova.mymarket.repositories.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public void addOrderItems(OrderItems orderItems){
        orderItemsRepository.save(orderItems);
    }
    public OrderItems getOrderItemsById(Long id){
        return orderItemsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("OrderItems doesn't exist"));
    }
    public void deleteOrderItemsById(Long id){
        orderItemsRepository.deleteById(id);
    }
    public List<OrderItems> getAllOrderItems(){
        return orderItemsRepository.findAll();
    }
    public void deleteAllOrderItems(){
        orderItemsRepository.deleteAll();
    }
    public void updateOrderItems(OrderItems orderItems){
        orderItemsRepository.save(orderItems);
    }
}

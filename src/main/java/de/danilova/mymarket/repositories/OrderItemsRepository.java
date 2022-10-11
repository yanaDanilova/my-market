package de.danilova.mymarket.repositories;

import de.danilova.mymarket.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository  extends JpaRepository<OrderItems,Long> {
}

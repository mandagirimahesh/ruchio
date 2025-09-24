package com.ruchio.Order_Service.Repository;

import com.ruchio.Order_Service.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

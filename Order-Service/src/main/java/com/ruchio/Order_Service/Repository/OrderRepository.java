package com.ruchio.Order_Service.Repository;

import com.ruchio.Order_Service.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

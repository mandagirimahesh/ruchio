package com.ruchio.Order_Service.Dto;

import com.ruchio.Order_Service.Entity.OrderItem;
import com.ruchio.Order_Service.Entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long customerId;
    private Long restaurantId;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private List<OrderItem> orders;
    private OrderStatus status;
}

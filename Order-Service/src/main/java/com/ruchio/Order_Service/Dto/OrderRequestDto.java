package com.ruchio.Order_Service.Dto;

import com.ruchio.Order_Service.Entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private Long customerId;
    private Long rastaurantId;
    private List<OrderItemDto> items;
}

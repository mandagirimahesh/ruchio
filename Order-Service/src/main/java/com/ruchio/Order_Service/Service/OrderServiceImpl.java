package com.ruchio.Order_Service.Service;

import com.ruchio.Order_Service.Dto.OrderRequestDto;
import com.ruchio.Order_Service.Dto.OrderResponseDto;

public class OrderServiceImpl extends IOrderService{
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        return null;
    }

    @Override
    public OrderResponseDto getOrderById(Long orderId) {
        return null;
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, String status) {
        return null;
    }
}

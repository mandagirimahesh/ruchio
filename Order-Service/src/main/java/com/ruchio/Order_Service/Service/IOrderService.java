package com.ruchio.Order_Service.Service;

import com.ruchio.Order_Service.Dto.OrderRequestDto;
import com.ruchio.Order_Service.Dto.OrderResponseDto;

public interface IOrderService {

    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);
    OrderResponseDto getOrderById(Long orderId);
    OrderResponseDto updateOrderStatus(Long orderId, String status);

}

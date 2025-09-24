package com.ruchio.Restaurant_Service.Service;


import com.ruchio.Restaurant_Service.Dto.MenuItemDto;
import com.ruchio.Restaurant_Service.Dto.RestaurentDto;

import java.util.List;

public interface IRestaurantService {
    RestaurentDto addRestaurent(RestaurentDto restaurentDto);
    List<RestaurentDto> getAllRestaurentDtos();
    MenuItemDto addMenuItem(Long restaurentId, MenuItemDto menuItemDto);
    List<MenuItemDto> getAllMenuByRestaurent(Long restaurentId);
}

package com.ruchio.Restaurant_Service.Controller;

import com.ruchio.Restaurant_Service.Dto.MenuItemDto;
import com.ruchio.Restaurant_Service.Dto.RestaurentDto;
import com.ruchio.Restaurant_Service.Service.IRestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurents")
@RequiredArgsConstructor
public class RestaurantController {

    private final IRestaurantService iRestaurantService;

    @PostMapping
    public RestaurentDto addRestaurent(@RequestBody RestaurentDto restaurentDto){
        return iRestaurantService.addRestaurent(restaurentDto);
    }

    @GetMapping
    public List<RestaurentDto> getAllRestaurents(){
        return iRestaurantService.getAllRestaurentDtos();
    }

    @PostMapping("/{restaurentId}/menu")
    public MenuItemDto addMenuItem(@PathVariable Long restaurentId, @RequestBody MenuItemDto menuItemDto){
        return iRestaurantService.addMenuItem(restaurentId, menuItemDto);
    }

    @GetMapping("/{restaurentId}/menu")
    public List<MenuItemDto> getAllMenuItems(@PathVariable Long restaurentId){
        return iRestaurantService.getAllMenuByRestaurent(restaurentId);
    }

}

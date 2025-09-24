package com.ruchio.Restaurant_Service.Service;

import com.ruchio.Restaurant_Service.Dto.MenuItemDto;
import com.ruchio.Restaurant_Service.Dto.RestaurentDto;
import com.ruchio.Restaurant_Service.Entity.MenuItem;
import com.ruchio.Restaurant_Service.Entity.Restaurent;
import com.ruchio.Restaurant_Service.Repository.MenuItemRepository;
import com.ruchio.Restaurant_Service.Repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements IRestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    private final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Override
    public RestaurentDto addRestaurent(RestaurentDto restaurentDto) {
        Restaurent restaurent = new Restaurent();
        restaurent.setName(restaurentDto.getName());
        restaurent.setAddress(restaurentDto.getAddress());

        log.info("*************************************************");
        log.trace("///////////////////////////////////////////");
        Restaurent restaurent1= restaurantRepository.save(restaurent);
        return new RestaurentDto(restaurent1.getId(),restaurent1.getName(),restaurent1.getAddress());
    }

    @Override
    public List<RestaurentDto> getAllRestaurentDtos() {
        return restaurantRepository
                .findAll()
                .stream()
                .map(r -> new RestaurentDto(r.getId(),r.getName(),r.getAddress()))
                .toList();
    }

    @Override
    public MenuItemDto addMenuItem(Long restaurentId, MenuItemDto menuItemDto) {
        Restaurent restaurent=restaurantRepository.findById(restaurentId)
                .orElseThrow(() -> new RuntimeException("Restaurent Not Found"));

        MenuItem menuItem = new MenuItem();
        menuItem.setItemName(menuItemDto.getItemName());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setRestaurent(restaurent);
        MenuItem saved = menuItemRepository.save(menuItem);
        return new MenuItemDto(saved.getId(),saved.getItemName(),saved.getPrice(),restaurentId);
    }

    @Override
    public List<MenuItemDto> getAllMenuByRestaurent(Long restaurentId) {
        Restaurent restaurent = restaurantRepository.findById(restaurentId)
                .orElseThrow(() -> new RuntimeException("Restaurent Not Found"));

        return restaurent.getMenu()
                .stream()
                .map(r -> new MenuItemDto(r.getId(),r.getItemName(),r.getPrice(),restaurentId))
                .toList();
    }
}

package com.ruchio.Restaurant_Service.Repository;

import com.ruchio.Restaurant_Service.Entity.Restaurent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurent, Long> {
}

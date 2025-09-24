package com.ruchio.Restaurant_Service.Repository;

import com.ruchio.Restaurant_Service.Entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}

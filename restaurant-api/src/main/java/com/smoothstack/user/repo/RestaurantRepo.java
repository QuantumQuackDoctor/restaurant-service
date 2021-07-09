package com.smoothstack.user.repo;

import com.database.ormlibrary.food.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepo extends CrudRepository<RestaurantEntity, Long> {

    @Query(value = "select * from restaurant_entity where search_primary like '%search%'", nativeQuery = true)
    public List<RestaurantEntity> searchRestaurantPrimary(@Param("search") String search);

    @Query(value = "select * from restaurant_entity where search_secondary like '%search%'", nativeQuery = true)
    public List<RestaurantEntity> searchRestaurantSecondary(@Param("search") String search);
}

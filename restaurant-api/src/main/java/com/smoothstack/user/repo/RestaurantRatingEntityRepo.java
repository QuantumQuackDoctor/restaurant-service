package com.smoothstack.user.repo;

import com.database.ormlibrary.food.RestaurantRatingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRatingEntityRepo extends CrudRepository<RestaurantRatingEntity, Long> {

    @Query(value = "select * from restaurant_entity_ratings where restaurant_entity_id = :restaurantId", nativeQuery = true)
    List<RestaurantRatingEntity> getRatingsByRestaurant (@Param("restaurantId") Long restaurantId);
}

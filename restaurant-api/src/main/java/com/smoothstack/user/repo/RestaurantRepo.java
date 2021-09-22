package com.smoothstack.user.repo;

import com.database.ormlibrary.food.RestaurantEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CrudRepository for Restaurants.
 */
@Repository
public interface RestaurantRepo extends CrudRepository<RestaurantEntity, Long> {

  public Optional<RestaurantEntity> findByName(String name);

  @Query(value = "select * from restaurant_entity where search_primary like %:search%",
      nativeQuery = true)
  public List<RestaurantEntity> searchRestaurantPrimary(@Param("search") String search);

  @Query(value = "select * from restaurant_entity where search_secondary like %:search%",
      nativeQuery = true)
  public List<RestaurantEntity> searchRestaurantSecondary(@Param("search") String search);
}

package com.smoothstack.user.repo;

import com.database.ormlibrary.food.RestaurantEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo extends CrudRepository<RestaurantEntity, Long> {

    @Query(value = "select * from restaurant_entity where search_primary like %:search%", nativeQuery = true)
    public List<RestaurantEntity> searchRestaurantPrimary(@Param("search") String search);

    @Query(value = "select * from restaurant_entity where search_secondary like %:search%", nativeQuery = true)
    public List<RestaurantEntity> searchRestaurantSecondary(@Param("search") String search);
}

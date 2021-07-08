package com.smoothstack.user.repo;

import com.database.ormlibrary.food.RestaurantEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantRepo extends PagingAndSortingRepository<RestaurantEntity, Long> {
}

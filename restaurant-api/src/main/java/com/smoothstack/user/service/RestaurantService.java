package com.smoothstack.user.service;

import com.database.ormlibrary.food.MenuItemEntity;
import org.springframework.stereotype.Service;

import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.repo.RestaurantRepo;

@Service
public class RestaurantService {

	private final RestaurantRepo restaurantRepo;

	public RestaurantService(RestaurantRepo restaurantRepo) {
		this.restaurantRepo = restaurantRepo;
	}

	public RestaurantEntity addRestaurant(RestaurantEntity restaurant) {
		restaurantRepo.save(restaurant);

		return restaurant;
	}

	public void addMenuItem(MenuItemEntity item) {

	}

	public RestaurantEntity getRestaurantById(Long id) {
		return restaurantRepo.findById(id).get();
	}

	public void deleteRestaurant(Long id) {
		restaurantRepo.deleteById(id);
	}

}

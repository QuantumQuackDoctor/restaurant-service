package com.smoothstack.user.service;


import com.database.ormlibrary.food.*;
import com.smoothstack.user.model.Restaurant;
import com.smoothstack.user.repo.RestaurantRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

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

    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }

}

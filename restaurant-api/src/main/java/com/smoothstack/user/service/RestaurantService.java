package com.smoothstack.user.service;

import com.database.ormlibrary.food.MenuItemEntity;
import com.database.ormlibrary.food.RestaurantRatingEntity;
import com.database.ormlibrary.user.UserEntity;
import com.smoothstack.user.model.RestaurantRatings;
import com.smoothstack.user.repo.RestaurantRatingEntityRepo;
import com.smoothstack.user.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.repo.RestaurantRepo;

import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Service
public class RestaurantService {

    private final RestaurantRepo restaurantRepo;
    private final UserRepo userRepo;
    private final RestaurantRatingEntityRepo restaurantRatingEntityRepo;
    private final ModelMapper modelMapper;

    public RestaurantService(RestaurantRepo restaurantRepo, UserRepo userRepo, RestaurantRatingEntityRepo restaurantRatingEntityRepo, ModelMapper modelMapper) {
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
        this.restaurantRatingEntityRepo = restaurantRatingEntityRepo;
        this.modelMapper = modelMapper;
    }

    public RestaurantEntity addRestaurant(RestaurantEntity restaurant) {
        restaurantRepo.save(restaurant);

        return restaurant;
    }

    public void addMenuItem(MenuItemEntity item) {
        if (item == null) {
            item = null;
        }
    }

    public RestaurantEntity getRestaurantById(Long id) {
        return restaurantRepo.findById(id).get();
    }

    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }

    public RestaurantRatings submitRating(Long userId, RestaurantRatings restaurantRatings) {
        UserEntity userEntity = userRepo.findById(userId).orElse(null);
        if (userEntity != null) {
            RestaurantRatingEntity restaurantRatingEntity = convertRatingsToEntity(restaurantRatings);
            restaurantRatingEntity.setUser(userEntity);
            updateRestaurantRatings(restaurantRatings);
            restaurantRatingEntityRepo.save(restaurantRatingEntity);
        }
        //TODO: add an exception for user not found later
        return null;
    }

    private void updateRestaurantRatings(RestaurantRatings restaurantRatings) {
        RestaurantEntity restaurantEntity = restaurantRepo.findById(restaurantRatings.getRestaurantId())
                .orElse(null);
        if (restaurantEntity != null) {
            List<RestaurantRatingEntity> ratingEntities = restaurantEntity.getRatings();
            ratingEntities.add(convertRatingsToEntity(restaurantRatings));
            OptionalDouble avgRating = ratingEntities.stream().mapToInt(RestaurantRatingEntity::getStars).average();
            if (avgRating.isPresent()) {
                restaurantEntity.setAverageRating((int) Math.ceil(avgRating.getAsDouble()));
                restaurantEntity.setRatings(ratingEntities);
            }
        }
    }

    private RestaurantRatingEntity convertRatingsToEntity(RestaurantRatings restaurantRatings) {

        return modelMapper.map(restaurantRatings, RestaurantRatingEntity.class);
    }

}

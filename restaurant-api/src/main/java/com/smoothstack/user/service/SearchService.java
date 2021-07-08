package com.smoothstack.user.service;

import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.repo.RestaurantRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    private final RestaurantRepo restaurantRepo;

    public SearchService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        Double radlat1 = Math.PI * lat1/180;
        Double radlat2 = Math.PI * lat2/180;
        Double radtheta = Math.PI * (lon1 - lon2) / 180;

        Double distance = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
        distance = Math.acos(distance);
        distance = distance * (180 / Math.PI) * 60 * 1.1515 * 1.609344;
        return distance;
    }

    public List<RestaurantEntity> search(String search, String geolocation) {
        String[] locations = geolocation.split(",");
        Iterable<RestaurantEntity> restaurants = restaurantRepo.findAll();
        ArrayList<RestaurantEntity> entities = new ArrayList<>();
        for(RestaurantEntity restaurant : restaurants) {
            if(calculateDistance(Double.parseDouble(locations[0]), Double.parseDouble(locations[1]),
                                 restaurant.getCoordinates().getLatitude(), restaurant.getCoordinates().getLongitude()) < 25) {
                entities.add(restaurant);
            }
        }
        return entities;
    }
}

package com.smoothstack.user.service;

import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.errors.InvalidSearchError;
import com.smoothstack.user.repo.RestaurantRepo;
import org.hibernate.dialect.Database;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Integer sortPrice(Integer price1, Integer price2) {
        if (price1 > price2) {
            return 1;
        }
        else if (price1 < price2) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public Integer sortStars(Integer star1, Integer star2) {
        if (star1 > star2) {
            return 1;
        }
        else if (star1 < star2) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public Boolean filterPrice(Integer price, Integer limit) {
        if (price <= limit) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean filterStars(Integer star, Integer limit) {
        if (star >= limit) {
            return true;
        }
        else {
            return false;
        }
    }



    public List<RestaurantEntity> search(String search, String geolocation, String sortType, String sortValue, Integer stars, Integer price) throws InvalidSearchError {
        String[] locations = geolocation.split(",");
        Iterable<RestaurantEntity> prime = restaurantRepo.searchRestaurantPrimary(search);
        Iterable<RestaurantEntity> second = restaurantRepo.searchRestaurantSecondary(search);

        List<RestaurantEntity> entities = new ArrayList<>();
        for(RestaurantEntity restaurant : prime) {
//            if(calculateDistance(Double.parseDouble(locations[0]), Double.parseDouble(locations[1]),
//                                 restaurant.getCoordinates().getLatitude(), restaurant.getCoordinates().getLongitude()) < 20) {
//                entities.add(restaurant);
//            }
            entities.add(restaurant);
        }
        for(RestaurantEntity restaurant : second) {
            entities.add(restaurant);
        }

        entities = sortFilterList(entities, sortType, sortValue, stars, price);


        return entities;
    }

    public List<RestaurantEntity> sortFilterList(List<RestaurantEntity> list, String sortType, String sortValue, Integer stars, Integer price) {

        if(stars != 0) {
            list = list.stream().filter(r -> filterStars(r.getAverageRating(), stars)).collect(Collectors.toList());
        }

        if(price != 5) {
            list = list.stream().filter(r -> filterPrice(r.getPriceRating(), price)).collect(Collectors.toList());
        }

        switch(sortType) {
            case "stars":
                if (sortValue == "high") {
                    list = list.stream().sorted((x, y) -> sortStars(y.getAverageRating(), x.getAverageRating())).collect(Collectors.toList());
                }
                else {
                    list = list.stream().sorted((x, y) -> sortStars(x.getAverageRating(), y.getAverageRating())).collect(Collectors.toList());
                }
                break;
            case "price":
                if (sortValue == "high") {
                    list = list.stream().sorted((x, y) -> sortPrice(y.getPriceRating(), x.getPriceRating())).collect(Collectors.toList());
                }
                else {
                    list = list.stream().sorted((x, y) -> sortPrice(x.getPriceRating(), y.getPriceRating())).collect(Collectors.toList());
                }
                break;
            default:
                break;
        }
        return list;
    }

    public List<RestaurantEntity> search(String search, String geolocation) throws InvalidSearchError {
        String[] locations = geolocation.split(",");
        
        List<RestaurantEntity> entities = new ArrayList<>();
        
        if(locations.length != 2) {
        	throw new InvalidSearchError("Location not specified");
        }
        
        if(search == "") {
        	Iterable<RestaurantEntity> all = restaurantRepo.findAll();
        	
        	for(RestaurantEntity one : all) {
        		entities.add(one);
        	}
        	
        	return entities;
        }
        
        Iterable<RestaurantEntity> prime = restaurantRepo.searchRestaurantPrimary(search);
        Iterable<RestaurantEntity> second = restaurantRepo.searchRestaurantSecondary(search);
        
        

        
        for(RestaurantEntity restaurant : prime) {
//            if(calculateDistance(Double.parseDouble(locations[0]), Double.parseDouble(locations[1]),
//                                 restaurant.getCoordinates().getLatitude(), restaurant.getCoordinates().getLongitude()) < 20) {
//                entities.add(restaurant);
//            }
            entities.add(restaurant);
        }
        for(RestaurantEntity restaurant : second) {
            entities.add(restaurant);
        }


        return entities;
    }


}

package com.smoothstack.user.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.database.ormlibrary.food.MenuItemEntity;
import com.database.ormlibrary.food.PromotionsEntity;
import com.database.ormlibrary.food.RestaurantRatingEntity;
import com.smoothstack.user.errors.RestaurantNotFoundException;
import com.smoothstack.user.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.errors.InvalidSearchError;
import com.smoothstack.user.repo.RestaurantRepo;

@Service
public class SearchService {

	private final RestaurantRepo restaurantRepo;
	private final ModelMapper modelMapper;

	public SearchService(RestaurantRepo restaurantRepo, ModelMapper modelMapper) {
		this.restaurantRepo = restaurantRepo;
		this.modelMapper = modelMapper;
	}

	public Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
		Double radlat1 = Math.PI * lat1 / 180;
		Double radlat2 = Math.PI * lat2 / 180;
		Double radtheta = Math.PI * (lon1 - lon2) / 180;

		Double distance = Math.sin(radlat1) * Math.sin(radlat2)
				+ Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
		distance = Math.acos(distance);
		distance = distance * (180 / Math.PI) * 60 * 1.1515 * 1.609344;
		return distance;
	}
	
	public Integer sortDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
		if (calculateDistance(lat1, lon1, lat2, lon2) > 0) {
			return 1;
		} else if (calculateDistance(lat1, lon1, lat2, lon2) < 0) {
			return -1;
		} else {
			return 0;
		}
	}

	public Integer sortPrice(Integer price1, Integer price2) {
		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}

	public Integer sortStars(Integer star1, Integer star2) {
		if (star1 > star2) {
			return 1;
		} else if (star1 < star2) {
			return -1;
		} else {
			return 0;
		}
	}

	public Restaurant getRestaurant(Long id) throws RestaurantNotFoundException {
		return convertToDTO(
				restaurantRepo.findById(id).orElseThrow(() -> new RestaurantNotFoundException("id doesn't exist"))
		);
	}

	public Restaurant getRestaurantByName(String name) throws RestaurantNotFoundException {
		return convertToDTO(
				restaurantRepo.findByName(name).orElseThrow(() -> new RestaurantNotFoundException("id doesn't exist"))
		);
	}

	public Boolean filterDistance(Double lat1, Double lon1, Double lat2, Double lon2, Double miles) {
		if (calculateDistance(lat1, lon1, lat2, lon2) < miles) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean filterPrice(Integer price, Integer limit) {
		if (price <= limit) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean filterStars(Integer star, Integer limit) {
		if (star >= limit) {
			return true;
		} else {
			return false;
		}
	}

	public List<Restaurant> search(String search, String geolocation, String sortType, String sortValue,
								   Integer stars, Integer price, Integer page, Integer size) throws InvalidSearchError {
		String[] locations = geolocation.split(",");
		Iterable<RestaurantEntity> prime = restaurantRepo.searchRestaurantPrimary(search);
		Iterable<RestaurantEntity> second = restaurantRepo.searchRestaurantSecondary(search);


		List<RestaurantEntity> entities = new ArrayList<>();
		for (RestaurantEntity restaurant : prime) {
			if(!entities.contains(restaurant)) {
				entities.add(restaurant);
			}
		}
		for (RestaurantEntity restaurant : second) {
			if(!entities.contains(restaurant)) {
				entities.add(restaurant);
			}
		}

		entities = sortFilterList(entities, sortType, sortValue, stars, price);

		entities = pageList(entities, page, size);


		return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Restaurant convertToDTO(RestaurantEntity entity){
		System.out.println(entity);
		//none of this was necessary, modelMapper gets all parameters.
		return modelMapper.map(entity, Restaurant.class);
	}



	public List<RestaurantEntity> sortFilterList(List<RestaurantEntity> list, String sortType, String sortValue,
			Integer stars, Integer price) {
		if (stars != null && stars != 0) {
			list = list.stream().filter(r -> filterStars(r.getAverageRating(), stars)).collect(Collectors.toList());
		}

		if (price != null && price != 5) {
			list = list.stream().filter(r -> filterPrice(r.getPriceRating(), price)).collect(Collectors.toList());
		}

		switch (sortType != null? sortType : "") {
		case "stars":
			if ("high".equals(sortValue)) {
				list = list.stream().sorted((x, y) -> sortStars(y.getAverageRating(), x.getAverageRating()))
						.collect(Collectors.toList());
				// Collections.sort(list, new SortByStars());
			} else {
				list = list.stream().sorted((x, y) -> sortStars(x.getAverageRating(), y.getAverageRating()))
						.collect(Collectors.toList());
				// Collections.sort(list, Collections.reverseOrder(new SortByStars()));
			}
			break;
		case "price":
			if ("high".equals(sortValue)) {
				list = list.stream().sorted((x, y) -> sortPrice(y.getPriceRating(), x.getPriceRating()))
						.collect(Collectors.toList());
			} else {
				list = list.stream().sorted((x, y) -> sortPrice(x.getPriceRating(), y.getPriceRating()))
						.collect(Collectors.toList());
			}
			break;
//		case "geolocation":
//			if ("high".equals(sortValue)) {
//				list = list.stream().sorted((x, y) -> sortDistance(y.getCoordinates().getLatitude(), y.getCoordinates().getLongitude(),
//                                 x.getCoordinates().getLatitude(), x.getCoordinates().getLongitude()))
//						.collect(Collectors.toList());
//			} else {
//				list = list.stream().sorted((x, y) -> sortDistance(x.getCoordinates().getLatitude(), x.getCoordinates().getLongitude(),
//						y.getCoordinates().getLatitude(), y.getCoordinates().getLongitude()))
//						.collect(Collectors.toList());
//			}
//			break;
		default:
			break;
		}
		return list;
	}

	public List<RestaurantEntity> pageList(List<RestaurantEntity> list, Integer page, Integer size) {
		//this can result in an index out of bounds exception
		return list.subList(page * size, Math.min((page + 1) * size, list.size()));
	}

	public List<Restaurant> search(String search, String geolocation) throws InvalidSearchError {
		String[] locations = geolocation.split(",");

		List<RestaurantEntity> entities = new ArrayList<>();

		if (locations.length != 2) {
			throw new InvalidSearchError("Location not specified");
		}

		if (search.equals("")) {
			Iterable<RestaurantEntity> all = restaurantRepo.findAll();

			for (RestaurantEntity one : all) {
				entities.add(one);
			}

			return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
		}

		Iterable<RestaurantEntity> prime = restaurantRepo.searchRestaurantPrimary(search);
		Iterable<RestaurantEntity> second = restaurantRepo.searchRestaurantSecondary(search);

		for (RestaurantEntity restaurant : prime) {
//            if(calculateDistance(Double.parseDouble(locations[0]), Double.parseDouble(locations[1]),
//                                 restaurant.getCoordinates().getLatitude(), restaurant.getCoordinates().getLongitude()) < 20) {
//                entities.add(restaurant);
//            }
			entities.add(restaurant);
		}
		for (RestaurantEntity restaurant : second) {
			entities.add(restaurant);
		}

		return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

}

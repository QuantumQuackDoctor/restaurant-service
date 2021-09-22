package com.smoothstack.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.ormlibrary.CoordinatesEmbeddable;
import com.database.ormlibrary.SearchEmbeddable;
import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.OpenApi2SpringBoot;
import com.smoothstack.user.service.RestaurantService;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(classes = { OpenApi2SpringBoot.class })
class RestaurantTest {
	@Autowired
	RestaurantService restaurantService;

	@Test
	@WithMockUser(roles = "user")
	void testInsertRestaurant() {
		// Generate a test Restaurant
		RestaurantEntity restaurantEntity = getTestEntity(1L);

		// Add Restaurant
		restaurantService.addRestaurant(restaurantEntity);

		// Get Restaurant and check it against the test Restaurant
		checkInsert(restaurantEntity, restaurantService.getRestaurantById(1L));

		// Delete Restaurant
		restaurantService.deleteRestaurant(1L);
	}

	public Boolean checkInsert(RestaurantEntity check, RestaurantEntity test) {

		// Check all fields to make sure they are the same
		assertEquals(check.getId(), test.getId());
		assertEquals(check.getName(), test.getName());
		assertEquals(check.getAverageTime(), test.getAverageTime());
		assertEquals(check.getAverageRating(), test.getAverageRating());
		assertEquals(check.getPriceRating(), test.getPriceRating());
		assertEquals(check.getAddress(), test.getAddress());
		assertEquals(check.getSearch().getSearchPrimary(), test.getSearch().getSearchPrimary());
		assertEquals(check.getSearch().getSearchSecondary(), test.getSearch().getSearchSecondary());
//		assertEquals(check.getCoordinates().getLatitude(), test.getCoordinates().getLatitude());
//		assertEquals(check.getCoordinates().getLongitude(), test.getCoordinates().getLongitude());

		return true;
	}

	public RestaurantEntity getTestEntity(Long id) {
		return getSampleEntity(id, "test", 5, 1, "food,test,place", "test1,test2,test3");
	}

	public RestaurantEntity getSampleEntity(Long id, String name, Integer stars, Integer price, String primary,
			String secondary) {
		// Set each field to test data
		RestaurantEntity restaurantEntity = new RestaurantEntity();
		restaurantEntity.setId(id);
		restaurantEntity.setName(name);
		restaurantEntity.setAverageTime(10);
		restaurantEntity.setAverageRating(stars);
		restaurantEntity.setPriceRating(price);
		restaurantEntity.setAddress("test ave");
		SearchEmbeddable searchEmbeddable = new SearchEmbeddable();
		searchEmbeddable.setSearchPrimary(primary);
		searchEmbeddable.setSearchSecondary(secondary);
		restaurantEntity.setSearch(searchEmbeddable);
		CoordinatesEmbeddable coordinatesEmbeddable = new CoordinatesEmbeddable();
		coordinatesEmbeddable.setLatitude(0.0);
		coordinatesEmbeddable.setLongitude(0.0);
//		restaurantEntity.setCoordinates(coordinatesEmbeddable);

		return restaurantEntity;
	}

}

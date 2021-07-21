package com.smoothstack.restaurant;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.smoothstack.user.errors.InvalidSearchError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.database.ormlibrary.CoordinatesEmbeddable;
import com.database.ormlibrary.SearchEmbeddable;
import com.database.ormlibrary.food.RestaurantEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.user.OpenAPI2SpringBoot;
import com.smoothstack.user.repo.RestaurantRepo;
import com.smoothstack.user.service.RestaurantService;
import com.smoothstack.user.service.SearchService;

@SpringBootTest(classes = { OpenAPI2SpringBoot.class })
@AutoConfigureMockMvc
class RestaurantApiTest {
	@MockBean(RestaurantRepo.class)
	RestaurantRepo restaurantRepo;
	@Autowired
	RestaurantService restaurantService;
	@MockBean
	SearchService searchService;
	@Autowired
	MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setup() throws InvalidSearchError {
		RestaurantEntity restaurantEntity = getTestEntity(1L);
		List<RestaurantEntity> list = new ArrayList<>();
		list.add(restaurantEntity);
		// Return the list of one restaurant when one is searched
		when(searchService.search("food", "0.0,0.0")).thenReturn(list);
	}

	@Test
	void testRestaurantApi() throws Exception {
		// Initialize a test entity
		RestaurantEntity restaurantEntity = getTestEntity(1L);

		// Insert a Restaurant
		mockMvc.perform(put("/restaurants").content(mapper.writeValueAsString(restaurantEntity))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


		mockMvc.perform(get("/restaurants").param("search", "food").param("geolocation", "0.0"))
				.andExpect(status().isBadRequest());

		// Delete a Restaurant
		mockMvc.perform(delete("/restaurants").content("1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
    @WithMockUser(roles = "user")
	void RestaurantSearch_ShouldReturnRestaurants() throws Exception {

		// Search with valid or invalid requests
		mockMvc.perform(get("/restaurants").param("search", "food").param("geolocation", "0.0,0.0"))
				.andExpect(status().isOk());
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
		restaurantEntity.setCoordinates(coordinatesEmbeddable);

		return restaurantEntity;
	}

}

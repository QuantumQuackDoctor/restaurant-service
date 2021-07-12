package com.smoothstack.restaurant;

import com.database.ormlibrary.CoordinatesEmbeddable;
import com.database.ormlibrary.SearchEmbeddable;
import com.database.ormlibrary.food.RestaurantEntity;
import com.database.ormlibrary.user.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smoothstack.user.Main;
import com.smoothstack.user.OpenAPI2SpringBoot;
import com.smoothstack.user.repo.RestaurantRepo;
import com.smoothstack.user.service.RestaurantService;
import com.smoothstack.user.service.SearchService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes={OpenAPI2SpringBoot.class})
@AutoConfigureMockMvc
public class SearchTest {
    @MockBean(RestaurantRepo.class)
    RestaurantRepo restaurantRepo;
    @MockBean
    RestaurantService restaurantService;
    @MockBean
    SearchService searchService;
    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();



    @Test
    void testInsertRestaurant() throws Exception {
        RestaurantEntity restaurantEntity = getTestEntity(1L);
        List<RestaurantEntity> list = new ArrayList<>();
        list.add(restaurantEntity);

        List<RestaurantEntity> sflist = getTestList();

        when(restaurantRepo.findAll()).thenReturn(list);
        when(searchService.search("food","0.0,0.0")).thenReturn(list);
        when(searchService.search("food","0.0,0.0", "stars", "high", 0, 5)).thenReturn(sflist);

        mockMvc.perform(put("/restaurant").content(mapper.writeValueAsString(restaurantEntity))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        String response = mockMvc.perform(get("/restaurant").param("search", "food")
                .param("geolocation", "0.0,0.0")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(searchService.search("food","0.0,0.0"));
        List<RestaurantEntity> rests = searchService.search("food","0.0,0.0", "stars", "high", 0, 5);

        for (RestaurantEntity rest : rests) {
            System.out.println(rest);
        }

        mockMvc.perform(delete("/restaurant").content("1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


    }



    public RestaurantEntity getTestEntity(Long id) {
        return getSampleEntity(id, "test", 5, 1, "food,test,place", "test1,test2,test3");
    }

    public List<RestaurantEntity> getTestList() {
        List<RestaurantEntity> list = new ArrayList<>();
        list.add(getSampleEntity(1L, "test", 5, 1, "test", "test1"));
        list.add(getSampleEntity(2L, "test", 5, 1, "test,place", "test2"));
        list.add(getSampleEntity(3L, "test", 4, 4, "food,place", "test3"));
        list.add(getSampleEntity(4L, "test", 4, 2, "food", "test1,test2,test3"));
        list.add(getSampleEntity(5L, "test", 2, 1, "place", "test1"));
        list.add(getSampleEntity(6L, "test", 2, 3, "food,test", "test"));
        list.add(getSampleEntity(7L, "test", 1, 3, "food,test,place", "food"));
        list.add(getSampleEntity(8L, "test", 1, 2, "food,test", "place"));
        list.add(getSampleEntity(9L, "test", 1, 4, "food,test", "test1,test2,test3"));
        list.add(getSampleEntity(10L, "test", 3, 1, "food,test,place", "test1,test2,test3"));

        return list;
    }

    public RestaurantEntity getSampleEntity(Long id, String name, Integer stars, Integer price, String primary, String secondary) {
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

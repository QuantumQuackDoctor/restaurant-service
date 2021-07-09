package com.smoothstack.restaurant;

import com.database.ormlibrary.food.RestaurantEntity;
import com.database.ormlibrary.user.UserEntity;
import com.smoothstack.user.repo.RestaurantRepo;
import com.smoothstack.user.service.RestaurantService;
import com.smoothstack.user.service.SearchService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SearchTest {
    @MockBean(RestaurantRepo.class)
    RestaurantRepo restaurantRepo;

    @Test
    void testInsertRestaurant() {

    }
}

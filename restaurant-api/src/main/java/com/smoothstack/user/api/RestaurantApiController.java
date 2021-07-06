package com.smoothstack.user.api;

import com.smoothstack.user.model.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
@Controller
@RequestMapping("${openapi.orchestrator.base-path:}")
public class RestaurantApiController implements RestaurantApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RestaurantApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Restaurant>> getFood(String search, String geolocation, String distance, String filterAllergens, String filterDietaryRestrictions, Integer stars, Integer page, Integer size) {
        // return RestaurantApi.super.getFood(search, geolocation, distance, filterAllergens, filterDietaryRestrictions, stars, page, size);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

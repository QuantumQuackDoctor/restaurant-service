package com.smoothstack.user.api;

import java.util.List;
import java.util.Optional;

import com.smoothstack.user.model.Restaurant;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import com.database.ormlibrary.food.RestaurantEntity;
import com.smoothstack.user.errors.InvalidSearchError;
import com.smoothstack.user.service.RestaurantService;
import com.smoothstack.user.service.SearchService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Controller
public class RestaurantApiController {

	private final NativeWebRequest request;
	private final SearchService searchService;
	private final RestaurantService restaurantService;

	@org.springframework.beans.factory.annotation.Autowired
	public RestaurantApiController(NativeWebRequest request, SearchService searchService,
			RestaurantService restaurantService) {
		this.request = request;
		this.searchService = searchService;
		this.restaurantService = restaurantService;
	}

	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@PreAuthorize("hasRole('ROLE_user')")
	@GetMapping(value = "/restaurants", produces = {"application/json"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK", response = Restaurant.class, responseContainer = "List")})
	@ApiOperation(value = "Search Restaurants", nickname = "getFood", notes = "Search food, matches to restaurant terms", response = Restaurant.class, responseContainer = "List", authorizations = {

			@Authorization(value = "JWT")
	}, tags = {"food",})
	public ResponseEntity<List<Restaurant>> getFood(
			@RequestParam(value = "search", required = true) @Valid @ApiParam(value = "Main search term", required = true) @NotNull String search,
			@RequestParam(value = "geolocation", required = true) @Valid @ApiParam(value = "Location to search around", required = true) @NotNull String geolocation,
			@RequestParam(value = "sort_type", required = false) @Valid @ApiParam("type of sort") String sortType,
			@RequestParam(value = "sort_values", required = false) @Valid @ApiParam("sort values") String sortValue,
			@RequestParam(value = "stars", required = false) @Valid @ApiParam(">= stars will be included") Integer stars,
			@RequestParam(value = "price", required = false) @Valid @ApiParam("<= price will be included") Integer price,
			@RequestParam(value = "page", required = false) @Valid @ApiParam("page to return indexed by 0") @Min(0) Integer page,
			@RequestParam(value = "size", required = false) @Valid @ApiParam("items in page") @Min(1) Integer size)
			throws InvalidSearchError {
		// return RestaurantApi.super.getFood(search, geolocation, distance,
		// filterAllergens, filterDietaryRestrictions, stars, page, size);

		return new ResponseEntity<List<Restaurant>>(searchService.search(search, geolocation, sortType, sortValue, stars, price, page, size), HttpStatus.OK);
//		return new ResponseEntity<List<Restaurant>>(searchService.search(search, geolocation, sortType, sortValue, stars, price), HttpStatus.OK);
	}

	@ExceptionHandler(InvalidSearchError.class)
	public ResponseEntity<String> invalidSearch(InvalidSearchError e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@PutMapping(value = "/restaurants", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Returns restaurant with populated imageId's, upload images at ids with POST /restaurant/image/{id}", response = RestaurantEntity.class),
			@ApiResponse(code = 401, message = "Access token is missing or invalid", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden")})
	@ApiOperation(value = "Admin Create Restaurant", nickname = "createRestaurant", notes = "Create new restaurant, images to be uploaded at POST /restaurant/image", response = RestaurantEntity.class, tags = {"food",})
	public ResponseEntity<RestaurantEntity> createRestaurant(@RequestBody(required = false) @Valid @ApiParam("Restaurant object with null imageId's") RestaurantEntity restaurant) {
		// return RestaurantApi.super.createRestaurant(restaurant);
		return new ResponseEntity<RestaurantEntity>(restaurantService.addRestaurant(restaurant), HttpStatus.OK);
	}

	@DeleteMapping(value = "/restaurants", produces = {"application/json"}, consumes = {"application/json"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Deleted"),
			@ApiResponse(code = 401, message = "Access token is missing or invalid", response = String.class),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found")})
	@ApiOperation(value = "Admin Delete Restaurant", nickname = "deleteRestaurant", notes = "delete restaurant", authorizations = {

			@Authorization(value = "JWT")
	}, tags = {"food",})
	public ResponseEntity<Void> deleteRestaurant(@RequestBody(required = false) @Valid @ApiParam("Restuarant id to delete") Long id) {
		restaurantService.deleteRestaurant(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	/**
	 * POST /restaurant/image/{imageId} : Admin Upload Image
	 * Uploads image at id, updates or creates document
	 *
	 * @param imageId  (required)
	 * @param image  (optional)
	 * @return Image uploaded (status code 200)
	 */
	@ApiOperation(value = "Admin Upload Image", nickname = "adminUploadImage", notes = "Uploads image at id, updates or creates document", authorizations = {

		@Authorization(value = "JWT")
		 }, tags={ "food", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Image uploaded") })
	@PostMapping(
		value = "/restaurants/image/{imageId}",
		consumes = { "multipart/form-data" }
	)
	public ResponseEntity<Void> adminUploadImage(@ApiParam(value = "", required = true) @PathVariable("imageId") String imageId, @ApiParam(value = "") @Valid @RequestPart(value = "image", required = false) MultipartFile image) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}

	/**
	 * GET /restaurant/image/{imageId} : Get image
	 * Returns image
	 *
	 * @param imageId  (required)
	 * @return Returns requested image (status code 200)
	 */
	@ApiOperation(value = "Get image", nickname = "getRestaurantImage", notes = "Returns image ", response = MultipartFile.class, authorizations = {

		@Authorization(value = "JWT")
		 }, tags={ "food", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Returns requested image", response = MultipartFile.class) })
	@GetMapping(
		value = "/restaurants/image/{imageId}",
		produces = { "image/_*" }
	)
	public ResponseEntity<MultipartFile> getRestaurantImage(@ApiParam(value = "", required = true) @PathVariable("imageId") String imageId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}

	/**
	 * PUT /restaurant/rating/{restaurantId} : Rate Restaurant
	 * Post new restaurant rating (Server: extract userId from JWT)
	 *
	 * @param restaurantId restaurant to update (required)
	 * @param stars  (optional)
	 * @param description  (optional)
	 * @param image  (optional)
	 * @return Rating uploaded successfully (status code 200)
	 *         or Missing field (status code 400)
	 *         or Access token is missing or invalid (status code 401)
	 *         or Forbidden (status code 403)
	 *         or Restaurant not found (status code 404)
	 */
	@ApiOperation(value = "Rate Restaurant", nickname = "newRating", notes = "Post new restaurant rating (Server: extract userId from JWT)", authorizations = {

		@Authorization(value = "JWT")
		 }, tags={ "food", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Rating uploaded successfully"),
		@ApiResponse(code = 400, message = "Missing field"),
		@ApiResponse(code = 401, message = "Access token is missing or invalid", response = String.class),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Restaurant not found") })
	@PutMapping(
		value = "/restaurants/rating/{restaurantId}",
		produces = { "application/json" },
		consumes = { "multipart/form-data" }
	)
	public ResponseEntity<Void> newRating(@ApiParam(value = "restaurant to update", required = true) @PathVariable("restaurantId") String restaurantId, @ApiParam(value = "") @Valid @RequestPart(value = "stars", required = false) Integer stars, @ApiParam(value = "") @Valid @RequestPart(value = "description", required = false) String description, @ApiParam(value = "") @Valid @RequestPart(value = "image", required = false) MultipartFile image) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}

	/**
	 * PATCH /restaurant : Admin Update Restaurant
	 * Update any properties except image, to update image use POST /restaurant/image/{imageId}
	 *
	 * @param restaurant Non null properties will be updated, id necessary (optional)
	 * @return Update successful (status code 200)
	 *         or Access token is missing or invalid (status code 401)
	 *         or Forbidden (status code 403)
	 *         or Not Found (status code 404)
	 */
	@ApiOperation(value = "Admin Update Restaurant", nickname = "updateRestaurant", notes = "Update any properties except image, to update image use POST /restaurant/image/{imageId}", authorizations = {

		@Authorization(value = "JWT")
		 }, tags={ "food", })
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Update successful"),
		@ApiResponse(code = 401, message = "Access token is missing or invalid", response = String.class),
		@ApiResponse(code = 403, message = "Forbidden"),
		@ApiResponse(code = 404, message = "Not Found") })
	@PatchMapping(
		value = "/restaurants",
		produces = { "application/json" },
		consumes = { "application/json", "application/xml" }
	)
	public ResponseEntity<Void> update


	(@ApiParam(value = "Non null properties will be updated, id necessary") @Valid @RequestBody(required = false) RestaurantEntity restaurant) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}
}

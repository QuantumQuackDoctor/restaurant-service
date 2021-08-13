package com.smoothstack.user.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Restaurant
 */
public class Restaurant   {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("iconId")
  private String iconId;

  @JsonProperty("backgroundId")
  private String backgroundId;

  @JsonProperty("menu")
  @Valid
  private List<RestaurantMenu> menu = null;

  @JsonProperty("promotions")
  @Valid
  private List<RestaurantPromotions> promotions = null;

  @JsonProperty("ratings")
  @Valid
  private List<RestaurantRatings> ratings = null;

  @JsonProperty("averageRating")
  private Integer averageRating;

  @JsonProperty("averageTime")
  private Integer averageTime;

  @JsonProperty("priceRating")
  private Integer priceRating;

  @JsonProperty("geolocation")
  private String geolocation;

  @JsonProperty("address")
  private String address;

  @JsonProperty("hours")
  private RestaurantHours hours;

  public Restaurant id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Restaurant name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Restaurant iconId(String iconId) {
    this.iconId = iconId;
    return this;
  }

  /**
   * Image path
   * @return iconId
  */
  @ApiModelProperty(value = "Image path")

  @Valid

  public String getIconId() {
    return iconId;
  }

  public void setIconId(String iconId) {
    this.iconId = iconId;
  }

  public Restaurant backgroundId(String backgroundId) {
    this.backgroundId = backgroundId;
    return this;
  }

  /**
   * Image path
   * @return backgroundId
  */
  @ApiModelProperty(value = "Image path")


  public String getBackgroundId() {
    return backgroundId;
  }

  public void setBackgroundId(String backgroundId) {
    this.backgroundId = backgroundId;
  }

  public Restaurant menu(List<RestaurantMenu> menu) {
    this.menu = menu;
    return this;
  }

  public Restaurant addMenuItem(RestaurantMenu menuItem) {
    if (this.menu == null) {
      this.menu = new ArrayList<>();
    }
    this.menu.add(menuItem);
    return this;
  }

  /**
   * Get menu
   * @return menu
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<RestaurantMenu> getMenu() {
    return menu;
  }

  public void setMenu(List<RestaurantMenu> menu) {
    this.menu = menu;
  }

  public Restaurant promotions(List<RestaurantPromotions> promotions) {
    this.promotions = promotions;
    return this;
  }

  public Restaurant addPromotionsItem(RestaurantPromotions promotionsItem) {
    if (this.promotions == null) {
      this.promotions = new ArrayList<>();
    }
    this.promotions.add(promotionsItem);
    return this;
  }

  /**
   * Get promotions
   * @return promotions
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<RestaurantPromotions> getPromotions() {
    return promotions;
  }

  public void setPromotions(List<RestaurantPromotions> promotions) {
    this.promotions = promotions;
  }

  public Restaurant ratings(List<RestaurantRatings> ratings) {
    this.ratings = ratings;
    return this;
  }

  public Restaurant addRatingsItem(RestaurantRatings ratingsItem) {
    if (this.ratings == null) {
      this.ratings = new ArrayList<>();
    }
    this.ratings.add(ratingsItem);
    return this;
  }

  /**
   * Get ratings
   * @return ratings
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<RestaurantRatings> getRatings() {
    return ratings;
  }

  public void setRatings(List<RestaurantRatings> ratings) {
    this.ratings = ratings;
  }

  public Restaurant averageRating(Integer averageRating) {
    this.averageRating = averageRating;
    return this;
  }

  /**
   * Get rating
   * minimum: 1
   * maximum: 5
   * @return rating
  */
  @ApiModelProperty(value = "")

@Min(1) @Max(5) 
  public Integer getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(Integer averageRating) {
    this.averageRating = averageRating;
  }

  public Restaurant averageTime(Integer averageTime) {
    this.averageTime = averageTime;
    return this;
  }

  /**
   * minutes, rounded to int
   * @return averageTime
  */
  @ApiModelProperty(value = "minutes, rounded to int")


  public Integer getAverageTime() {
    return averageTime;
  }

  public void setAverageTime(Integer averageTime) {
    this.averageTime = averageTime;
  }

  public Restaurant priceRating(Integer priceRating) {
    this.priceRating = priceRating;
    return this;
  }

  /**
   * Get priceRating
   * minimum: 1
   * maximum: 3
   * @return priceRating
  */
  @ApiModelProperty(value = "")

@Min(1) @Max(3) 
  public Integer getPriceRating() {
    return priceRating;
  }

  public void setPriceRating(Integer priceRating) {
    this.priceRating = priceRating;
  }

  public Restaurant geolocation(String geolocation) {
    this.geolocation = geolocation;
    return this;
  }

  /**
   * Get geolocation
   * @return geolocation
  */
  @ApiModelProperty(value = "")


  public String getGeolocation() {
    return geolocation;
  }

  public void setGeolocation(String geolocation) {
    this.geolocation = geolocation;
  }

  public Restaurant address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @ApiModelProperty(value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Restaurant hours(RestaurantHours hours) {
    this.hours = hours;
    return this;
  }

  /**
   * Get hours
   * @return hours
  */
  @ApiModelProperty(value = "")

  @Valid

  public RestaurantHours getHours() {
    return hours;
  }

  public void setHours(RestaurantHours hours) {
    this.hours = hours;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Restaurant restaurant = (Restaurant) o;
    return Objects.equals(this.id, restaurant.id) &&
        Objects.equals(this.name, restaurant.name) &&
        Objects.equals(this.iconId, restaurant.iconId) &&
        Objects.equals(this.backgroundId, restaurant.backgroundId) &&
        Objects.equals(this.menu, restaurant.menu) &&
        Objects.equals(this.promotions, restaurant.promotions) &&
        Objects.equals(this.ratings, restaurant.ratings) &&
        Objects.equals(this.averageRating, restaurant.averageRating) &&
        Objects.equals(this.averageTime, restaurant.averageTime) &&
        Objects.equals(this.priceRating, restaurant.priceRating) &&
        Objects.equals(this.geolocation, restaurant.geolocation) &&
        Objects.equals(this.address, restaurant.address) &&
        Objects.equals(this.hours, restaurant.hours);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, iconId, backgroundId, menu, promotions, ratings, averageRating, averageTime, priceRating, geolocation, address, hours);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Restaurant {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    iconId: ").append(toIndentedString(iconId)).append("\n");
    sb.append("    backgroundId: ").append(toIndentedString(backgroundId)).append("\n");
    sb.append("    menu: ").append(toIndentedString(menu)).append("\n");
    sb.append("    promotions: ").append(toIndentedString(promotions)).append("\n");
    sb.append("    ratings: ").append(toIndentedString(ratings)).append("\n");
    sb.append("    rating: ").append(toIndentedString(averageRating)).append("\n");
    sb.append("    averageTime: ").append(toIndentedString(averageTime)).append("\n");
    sb.append("    priceRating: ").append(toIndentedString(priceRating)).append("\n");
    sb.append("    geolocation: ").append(toIndentedString(geolocation)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    hours: ").append(toIndentedString(hours)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


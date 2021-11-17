package com.smoothstack.user.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

/**
 * RestaurantRatings
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
public class RestaurantRatings   {
  @JsonProperty("image")
  private String imageId;

  @JsonProperty("stars")
  private Integer stars;

  @JsonProperty("description")
  private String description;

  @JsonProperty ("restaurantId")
  private Long restaurantId;

  public RestaurantRatings image(String imageId) {
    this.imageId = imageId;
    return this;
  }

  public Long getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(Long restaurantId) {
    this.restaurantId = restaurantId;
  }

  /**
   * filepath
   * @return image
  */
  @ApiModelProperty(value = "filepath")


  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public RestaurantRatings stars(Integer stars) {
    this.stars = stars;
    return this;
  }

  /**
   * Get stars
   * minimum: 1
   * maximum: 5
   * @return stars
  */
  @ApiModelProperty(value = "")

@Min(1) @Max(5) 
  public Integer getStars() {
    return stars;
  }

  public void setStars(Integer stars) {
    this.stars = stars;
  }

  public RestaurantRatings description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantRatings restaurantRatings = (RestaurantRatings) o;
    return Objects.equals(this.imageId, restaurantRatings.imageId) &&
        Objects.equals(this.stars, restaurantRatings.stars) &&
        Objects.equals(this.description, restaurantRatings.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageId, stars, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantRatings {\n");
    
    sb.append("    image: ").append(toIndentedString(imageId)).append("\n");
    sb.append("    stars: ").append(toIndentedString(stars)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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


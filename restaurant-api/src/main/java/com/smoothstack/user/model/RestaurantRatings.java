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
  private String image;

  @JsonProperty("stars")
  private Integer stars;

  @JsonProperty("description")
  private String description;

  public RestaurantRatings image(String image) {
    this.image = image;
    return this;
  }

  /**
   * filepath
   * @return image
  */
  @ApiModelProperty(value = "filepath")


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
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
    return Objects.equals(this.image, restaurantRatings.image) &&
        Objects.equals(this.stars, restaurantRatings.stars) &&
        Objects.equals(this.description, restaurantRatings.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image, stars, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantRatings {\n");
    
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
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


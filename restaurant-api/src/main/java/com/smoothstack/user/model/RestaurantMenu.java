package com.smoothstack.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * RestaurantMenu.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
public class RestaurantMenu   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("imgPath")
  private String imageId;

  @JsonProperty("allergens")
  private String allergens;

  @JsonProperty("price")
  private Float price;

  @JsonProperty("description")
  private String description;

  @JsonProperty("configurations")
  @Valid
  private List<String> configurations = null;

  public RestaurantMenu name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name.

   * @return name
  */
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RestaurantMenu imageId(String imageId) {
    this.imageId = imageId;
    return this;
  }

  /**
   * Get imageId.

   * @return imageId
  */
  @ApiModelProperty(value = "")


  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public RestaurantMenu allergens(String allergens) {
    this.allergens = allergens;
    return this;
  }

  /**
   * Get allergens.

   * @return allergens
  */
  @ApiModelProperty(value = "")


  public String getAllergens() {
    return allergens;
  }

  public void setAllergens(String allergens) {
    this.allergens = allergens;
  }

  public RestaurantMenu price(Float price) {
    this.price = price;
    return this;
  }

  /**
   * Get price.

   * @return price
   */
  @ApiModelProperty(value = "")

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public RestaurantMenu description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description.

   * @return description
   */
  @ApiModelProperty(value = "")

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RestaurantMenu configurations(List<String> configurations) {
    this.configurations = configurations;
    return this;
  }

  /**
   * Add configurations.

   * @return menu
   */
  public RestaurantMenu addConfigurationsItem(String configurationsItem) {
    if (this.configurations == null) {
      this.configurations = new ArrayList<>();
    }
    this.configurations.add(configurationsItem);
    return this;
  }

  /**
   * Get configurations.

   * @return configurations
  */
  @ApiModelProperty(value = "")


  public List<String> getConfigurations() {
    return configurations;
  }

  public void setConfigurations(List<String> configurations) {
    this.configurations = configurations;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantMenu restaurantMenu = (RestaurantMenu) o;
    return Objects.equals(this.name, restaurantMenu.name)
        && Objects.equals(this.imageId, restaurantMenu.imageId)
        && Objects.equals(this.allergens, restaurantMenu.allergens)
        && Objects.equals(this.configurations, restaurantMenu.configurations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, imageId, allergens, configurations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantMenu {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    imgPath: ").append(toIndentedString(imageId)).append("\n");
    sb.append("    allergens: ").append(toIndentedString(allergens)).append("\n");
    sb.append("    configurations: ").append(toIndentedString(configurations)).append("\n");
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


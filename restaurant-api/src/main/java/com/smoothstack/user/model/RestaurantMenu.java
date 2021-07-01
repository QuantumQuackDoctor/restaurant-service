package com.smoothstack.user.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * RestaurantMenu
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
public class RestaurantMenu   {
  @JsonProperty("name")
  private String name;

  @JsonProperty("imgPath")
  private String imgPath;

  @JsonProperty("allergens")
  private String allergens;

  @JsonProperty("dietaryRestrictions")
  private String dietaryRestrictions;

  @JsonProperty("configurations")
  @Valid
  private List<String> configurations = null;

  public RestaurantMenu name(String name) {
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

  public RestaurantMenu imgPath(String imgPath) {
    this.imgPath = imgPath;
    return this;
  }

  /**
   * Get imgPath
   * @return imgPath
  */
  @ApiModelProperty(value = "")


  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public RestaurantMenu allergens(String allergens) {
    this.allergens = allergens;
    return this;
  }

  /**
   * Get allergens
   * @return allergens
  */
  @ApiModelProperty(value = "")


  public String getAllergens() {
    return allergens;
  }

  public void setAllergens(String allergens) {
    this.allergens = allergens;
  }

  public RestaurantMenu dietaryRestrictions(String dietaryRestrictions) {
    this.dietaryRestrictions = dietaryRestrictions;
    return this;
  }

  /**
   * Get dietaryRestrictions
   * @return dietaryRestrictions
  */
  @ApiModelProperty(value = "")


  public String getDietaryRestrictions() {
    return dietaryRestrictions;
  }

  public void setDietaryRestrictions(String dietaryRestrictions) {
    this.dietaryRestrictions = dietaryRestrictions;
  }

  public RestaurantMenu configurations(List<String> configurations) {
    this.configurations = configurations;
    return this;
  }

  public RestaurantMenu addConfigurationsItem(String configurationsItem) {
    if (this.configurations == null) {
      this.configurations = new ArrayList<>();
    }
    this.configurations.add(configurationsItem);
    return this;
  }

  /**
   * Get configurations
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
    return Objects.equals(this.name, restaurantMenu.name) &&
        Objects.equals(this.imgPath, restaurantMenu.imgPath) &&
        Objects.equals(this.allergens, restaurantMenu.allergens) &&
        Objects.equals(this.dietaryRestrictions, restaurantMenu.dietaryRestrictions) &&
        Objects.equals(this.configurations, restaurantMenu.configurations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, imgPath, allergens, dietaryRestrictions, configurations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantMenu {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    imgPath: ").append(toIndentedString(imgPath)).append("\n");
    sb.append("    allergens: ").append(toIndentedString(allergens)).append("\n");
    sb.append("    dietaryRestrictions: ").append(toIndentedString(dietaryRestrictions)).append("\n");
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


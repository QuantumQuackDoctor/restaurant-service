package com.smoothstack.user.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.Valid;

/**
 * RestaurantPromotions
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
public class RestaurantPromotions   {
  /**
   * Gets or Sets promotionType
   */
  public enum PromotionTypeEnum {
    VETERAN("veteran"),
    
    ELDERLY("elderly"),
    
    BULK("bulk"),
    
    SPECIAL("special");

    private String value;

    PromotionTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static PromotionTypeEnum fromValue(String value) {
      for (PromotionTypeEnum b : PromotionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("promotionType")
  private PromotionTypeEnum promotionType;

  @JsonProperty("condition")
  private String condition;

  @JsonProperty("discount")
  private BigDecimal discount;

  /**
   * Gets or Sets discountType
   */
  public enum DiscountTypeEnum {
    PERCENT("percent"),
    
    FLAT("flat");

    private String value;

    DiscountTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DiscountTypeEnum fromValue(String value) {
      for (DiscountTypeEnum b : DiscountTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("discountType")
  private DiscountTypeEnum discountType;

  public RestaurantPromotions promotionType(PromotionTypeEnum promotionType) {
    this.promotionType = promotionType;
    return this;
  }

  /**
   * Get promotionType
   * @return promotionType
  */
  @ApiModelProperty(value = "")


  public PromotionTypeEnum getPromotionType() {
    return promotionType;
  }

  public void setPromotionType(PromotionTypeEnum promotionType) {
    this.promotionType = promotionType;
  }

  public RestaurantPromotions condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * figure this out better
   * @return condition
  */
  @ApiModelProperty(value = "figure this out better")


  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public RestaurantPromotions discount(BigDecimal discount) {
    this.discount = discount;
    return this;
  }

  /**
   * Get discount
   * @return discount
  */
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getDiscount() {
    return discount;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public RestaurantPromotions discountType(DiscountTypeEnum discountType) {
    this.discountType = discountType;
    return this;
  }

  /**
   * Get discountType
   * @return discountType
  */
  @ApiModelProperty(value = "")


  public DiscountTypeEnum getDiscountType() {
    return discountType;
  }

  public void setDiscountType(DiscountTypeEnum discountType) {
    this.discountType = discountType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantPromotions restaurantPromotions = (RestaurantPromotions) o;
    return Objects.equals(this.promotionType, restaurantPromotions.promotionType) &&
        Objects.equals(this.condition, restaurantPromotions.condition) &&
        Objects.equals(this.discount, restaurantPromotions.discount) &&
        Objects.equals(this.discountType, restaurantPromotions.discountType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(promotionType, condition, discount, discountType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantPromotions {\n");
    
    sb.append("    promotionType: ").append(toIndentedString(promotionType)).append("\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
    sb.append("    discount: ").append(toIndentedString(discount)).append("\n");
    sb.append("    discountType: ").append(toIndentedString(discountType)).append("\n");
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


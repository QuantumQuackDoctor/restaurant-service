package com.smoothstack.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;

/**
 * RestaurantHours.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
public class RestaurantHours   {
  @JsonProperty("MON")
  private String mon;

  @JsonProperty("TUE")
  private String tue;

  @JsonProperty("WED")
  private String wed;

  @JsonProperty("THU")
  private String thu;

  @JsonProperty("FRI")
  private String fri;

  @JsonProperty("SAT")
  private String sat;

  @JsonProperty("SUN")
  private String sun;

  public RestaurantHours mon(String mon) {
    this.mon = mon;
    return this;
  }

  /**
   * Get MON.

   * @return MON
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getMon() {
    return mon;
  }

  public void setMon(String mon) {
    this.mon = mon;
  }

  public RestaurantHours tue(String tue) {
    this.tue = tue;
    return this;
  }

  /**
   * Get TUE.

   * @return TUE
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getTue() {
    return tue;
  }

  public void setTue(String tue) {
    this.tue = tue;
  }

  public RestaurantHours wed(String wed) {
    this.wed = wed;
    return this;
  }

  /**
   * Get WED.

   * @return WED
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getWed() {
    return wed;
  }

  public void setWed(String wed) {
    this.wed = wed;
  }

  public RestaurantHours thu(String thu) {
    this.thu = thu;
    return this;
  }

  /**
   * Get THU.

   * @return THU
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getThu() {
    return thu;
  }

  public void setThu(String thu) {
    this.thu = thu;
  }

  public RestaurantHours fri(String fri) {
    this.fri = fri;
    return this;
  }

  /**
   * Get FRI.

   * @return FRI
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getFri() {
    return fri;
  }

  public void setFri(String fri) {
    this.fri = fri;
  }

  public RestaurantHours sat(String sat) {
    this.sat = sat;
    return this;
  }

  /**
   * Get SAT.

   * @return SAT
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getSat() {
    return sat;
  }

  public void setSat(String sat) {
    this.sat = sat;
  }

  public RestaurantHours sun(String sun) {
    this.sun = sun;
    return this;
  }

  /**
   * Get SUN.

   * @return SUN
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getSun() {
    return sun;
  }

  public void setSun(String sun) {
    this.sun = sun;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestaurantHours restaurantHours = (RestaurantHours) o;
    return Objects.equals(this.mon, restaurantHours.mon)
        && Objects.equals(this.tue, restaurantHours.tue)
        && Objects.equals(this.wed, restaurantHours.wed)
        && Objects.equals(this.thu, restaurantHours.thu)
        && Objects.equals(this.fri, restaurantHours.fri)
        && Objects.equals(this.sat, restaurantHours.sat)
        && Objects.equals(this.sun, restaurantHours.sun);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mon, tue, wed, thu, fri, sat, sun);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantHours {\n");
    
    sb.append("    MON: ").append(toIndentedString(mon)).append("\n");
    sb.append("    TUE: ").append(toIndentedString(tue)).append("\n");
    sb.append("    WED: ").append(toIndentedString(wed)).append("\n");
    sb.append("    THU: ").append(toIndentedString(thu)).append("\n");
    sb.append("    FRI: ").append(toIndentedString(fri)).append("\n");
    sb.append("    SAT: ").append(toIndentedString(sat)).append("\n");
    sb.append("    SUN: ").append(toIndentedString(sun)).append("\n");
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


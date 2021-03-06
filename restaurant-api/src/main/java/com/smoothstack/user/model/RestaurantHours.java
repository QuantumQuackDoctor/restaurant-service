package com.smoothstack.user.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;

/**
 * RestaurantHours
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-06-30T22:10:31.533216200-06:00[America/Denver]")
public class RestaurantHours   {
  @JsonProperty("MON")
  private String MON;

  @JsonProperty("TUE")
  private String TUE;

  @JsonProperty("WED")
  private String WED;

  @JsonProperty("THU")
  private String THU;

  @JsonProperty("FRI")
  private String FRI;

  @JsonProperty("SAT")
  private String SAT;

  @JsonProperty("SUN")
  private String SUN;

  public RestaurantHours MON(String MON) {
    this.MON = MON;
    return this;
  }

  /**
   * Get MON
   * @return MON
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getMON() {
    return MON;
  }

  public void setMON(String MON) {
    this.MON = MON;
  }

  public RestaurantHours TUE(String TUE) {
    this.TUE = TUE;
    return this;
  }

  /**
   * Get TUE
   * @return TUE
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getTUE() {
    return TUE;
  }

  public void setTUE(String TUE) {
    this.TUE = TUE;
  }

  public RestaurantHours WED(String WED) {
    this.WED = WED;
    return this;
  }

  /**
   * Get WED
   * @return WED
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getWED() {
    return WED;
  }

  public void setWED(String WED) {
    this.WED = WED;
  }

  public RestaurantHours THU(String THU) {
    this.THU = THU;
    return this;
  }

  /**
   * Get THU
   * @return THU
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getTHU() {
    return THU;
  }

  public void setTHU(String THU) {
    this.THU = THU;
  }

  public RestaurantHours FRI(String FRI) {
    this.FRI = FRI;
    return this;
  }

  /**
   * Get FRI
   * @return FRI
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getFRI() {
    return FRI;
  }

  public void setFRI(String FRI) {
    this.FRI = FRI;
  }

  public RestaurantHours SAT(String SAT) {
    this.SAT = SAT;
    return this;
  }

  /**
   * Get SAT
   * @return SAT
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getSAT() {
    return SAT;
  }

  public void setSAT(String SAT) {
    this.SAT = SAT;
  }

  public RestaurantHours SUN(String SUN) {
    this.SUN = SUN;
    return this;
  }

  /**
   * Get SUN
   * @return SUN
  */
  @ApiModelProperty(value = "")

  @Valid

  public String getSUN() {
    return SUN;
  }

  public void setSUN(String SUN) {
    this.SUN = SUN;
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
    return Objects.equals(this.MON, restaurantHours.MON) &&
        Objects.equals(this.TUE, restaurantHours.TUE) &&
        Objects.equals(this.WED, restaurantHours.WED) &&
        Objects.equals(this.THU, restaurantHours.THU) &&
        Objects.equals(this.FRI, restaurantHours.FRI) &&
        Objects.equals(this.SAT, restaurantHours.SAT) &&
        Objects.equals(this.SUN, restaurantHours.SUN);
  }

  @Override
  public int hashCode() {
    return Objects.hash(MON, TUE, WED, THU, FRI, SAT, SUN);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RestaurantHours {\n");
    
    sb.append("    MON: ").append(toIndentedString(MON)).append("\n");
    sb.append("    TUE: ").append(toIndentedString(TUE)).append("\n");
    sb.append("    WED: ").append(toIndentedString(WED)).append("\n");
    sb.append("    THU: ").append(toIndentedString(THU)).append("\n");
    sb.append("    FRI: ").append(toIndentedString(FRI)).append("\n");
    sb.append("    SAT: ").append(toIndentedString(SAT)).append("\n");
    sb.append("    SUN: ").append(toIndentedString(SUN)).append("\n");
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


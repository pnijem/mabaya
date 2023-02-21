package com.mabaya.advertise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Builder;

@Builder
public class ProductResponse implements Serializable {

  @JsonProperty("id")
  private Integer id;


  @JsonProperty("title")
  private String title;

  @JsonProperty("category")
  private String category;

  @JsonProperty("price")
  private Double price;

  @JsonProperty("serial_number")
  private String serialNumber;

}

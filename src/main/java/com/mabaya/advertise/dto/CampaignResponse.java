package com.mabaya.advertise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mabaya.advertise.model.Product;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Builder;

@Builder
public class CampaignResponse implements Serializable {

  @JsonProperty("id")
  private Integer id;


  @JsonProperty("bid")
  private Double bid;
  @JsonProperty("name")
  private String name;

  @JsonProperty("start_date")
  private Long startDate;


  @JsonProperty("products")
  List<Product> products;

  @JsonProperty("categories")
  Set<String> categories;


}

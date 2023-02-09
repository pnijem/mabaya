package com.mabaya.advesrtise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseBody {

  private Long id;

  private String title;

  private String category; // enum

  private Double price;

  @JsonProperty("serial_number")
  private String serialNumber;

}

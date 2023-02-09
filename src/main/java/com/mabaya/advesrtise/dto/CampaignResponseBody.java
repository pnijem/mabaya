package com.mabaya.advesrtise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampaignResponseBody {

  private Long id;

  private String name;

  @JsonProperty("start_date")
  private Long startDate;

  private List<Integer> products;

  private Double bid;

}

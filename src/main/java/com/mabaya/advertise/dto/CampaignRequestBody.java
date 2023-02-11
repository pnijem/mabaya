package com.mabaya.advertise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CampaignRequestBody {

  @NotBlank
  private String name;

  @NotNull
  @JsonProperty("start_date")
  private Long startDate;

  private List<Integer> products;

  @NotNull
  private Double bid;


}

package com.mabaya.advertise.model;

import com.mabaya.advertise.converter.IntegerListToJsonConverter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "campaigns")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campaign implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  private Double bid;
  private String name;

  @Column(name = "start_date")
  private Long startDate;

  @OneToOne(mappedBy = "campaign")
  private CampaignCategories campaignCategories;
  @Convert(converter = IntegerListToJsonConverter.class)
  private List<Integer> products;


}

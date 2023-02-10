package com.mabaya.advertise.model;

import com.mabaya.advertise.converter.IntegerListToJsonConverter;
import com.mabaya.advertise.converter.StringSetToJsonConverter;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "campaigns", schema = "mabaya")
@SecondaryTable(name = "campaign_categories", pkJoinColumns = @PrimaryKeyJoinColumn(name = "campaign_id"), schema = "mabaya")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignAll {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;
  private Double bid;
  private String name;
  @Column(name = "start_date")

  private Long startDate;

  @Convert(converter = IntegerListToJsonConverter.class)
  private List<Integer> products;

  @Convert(converter = StringSetToJsonConverter.class)
  private Set<String> categories;

}

package com.mabaya.advertise.model;

import com.mabaya.advertise.converter.StringSetToJsonConverter;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "campaign_categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignCategories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "campaign_id", nullable = false)
  private Integer campaignId;

  @Convert(converter = StringSetToJsonConverter.class)
  private Set<String> categories;
}

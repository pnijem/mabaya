package com.mabaya.advertise.model;

import com.mabaya.advertise.converter.StringSetToJsonConverter;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class CampaignCategories implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "campaign_id", referencedColumnName = "id", nullable = false)
  private Campaign campaign;

  @Convert(converter = StringSetToJsonConverter.class)
  private Set<String> categories;
}

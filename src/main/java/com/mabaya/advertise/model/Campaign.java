package com.mabaya.advertise.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "campaign_product",
      joinColumns = @JoinColumn(name = "campaign_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Product> products;


}

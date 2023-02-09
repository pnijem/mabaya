package com.mabaya.advesrtise.model;

import com.mabaya.advesrtise.converter.JpaConverterJson;
import java.util.List;
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
@Table(name = "campaigns")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Campaign {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  private Double bid;
  private String name;

  @Column(name = "start_date")
  private Long startDate;

  @Convert(converter = JpaConverterJson.class)
  private List<Long> products;

}

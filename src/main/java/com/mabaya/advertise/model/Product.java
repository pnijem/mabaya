package com.mabaya.advertise.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(length = 10)
  private String title;

  private String category;

  private Double price;

  @Column(name = "serial_number", length = 36)
  private String serialNumber;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<Campaign> campaign;

}

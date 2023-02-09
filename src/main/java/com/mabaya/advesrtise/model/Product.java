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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  private String title;

  private String category; // enum

  private Double price;

  @Column(name = "serial_number")
  private String serialNumber;

  @Convert(converter = JpaConverterJson.class)
  private List<Long> campaigns;

}

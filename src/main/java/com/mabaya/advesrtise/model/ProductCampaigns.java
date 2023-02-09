//package com.mabaya.advesrtise.model;
//
//
//import com.mabaya.advesrtise.converter.JpaConverterJson;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Convert;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@Builder
//@Entity
//@Table(name = "product_campaigns")
//@NoArgsConstructor
//@AllArgsConstructor
//public class ProductCampaigns {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE)
//  @Column(name = "id", nullable = false)
//  private Long id;
//
//  @Column(name = "product_id")
//  private Long productId;
//
//  @Convert(converter = JpaConverterJson.class)
//  private List<Long> campaigns;
//}

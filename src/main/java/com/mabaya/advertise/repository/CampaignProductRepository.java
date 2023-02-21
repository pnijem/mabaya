package com.mabaya.advertise.repository;

import com.mabaya.advertise.model.CampaignProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignProductRepository extends JpaRepository<CampaignProduct, Integer> {

  @Query(value = "SELECT product_id" +
      " FROM campaign_product"
      + " WHERE campaign_id=:id", nativeQuery = true)
  List<Integer> getCampaignProducts(int id);

}

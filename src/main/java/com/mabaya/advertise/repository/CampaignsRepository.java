package com.mabaya.advertise.repository;

import com.mabaya.advertise.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepository extends JpaRepository<Campaign, Integer> {


  @Query(value = "SELECT c.id as id, name, start_date, products, MAX(c.bid) as bid" +
      " FROM campaigns c INNER JOIN campaign_categories cc ON c.id=cc.campaign_id"
      + " WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(c.start_date)) <= 10 "
      + " AND JSON_CONTAINS(cc.categories,  CONCAT('\"',:category,'\"'))"
      + " GROUP BY c.id"
      + " LIMIT 1", nativeQuery = true)
  Campaign getActiveCampaignsWithHighestBid(String category);

  @Query(value = "SELECT c.id as id, name, start_date, products, MAX(c.bid) as bid" +
      " FROM campaigns c INNER JOIN campaign_categories cc ON c.id=cc.campaign_id"
      + " WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(c.start_date)) <= 10"
      + " GROUP BY c.id"
      + " LIMIT 1", nativeQuery = true)
  Campaign getActiveCampaignsWithHighestBid();
}

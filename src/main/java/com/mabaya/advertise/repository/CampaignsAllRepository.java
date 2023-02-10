package com.mabaya.advertise.repository;

import com.mabaya.advertise.model.CampaignAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsAllRepository extends JpaRepository<CampaignAll, Integer> {

  /**
   * Campaign is considered active for the 10 days following its start-date. This method gets the
   * configurable number we consider a campaign is active since its start date and a category.
   *
   * @return list of active campaigns per category
   */
  @Query(value = "SELECT *, MAX(bid)" +
      " FROM campaigns c INNER JOIN campaign_categories cc ON c.id=cc.campaign_id"
      + " WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(c.start_date)) <= 10 "
      + " AND JSON_CONTAINS(cc.categories,  CONCAT('\"',:category,'\"'))"
      + " GROUP BY c.id", nativeQuery = true)
  CampaignAll getActiveCampaignsWithHighestBid(String category);

  /**
   * Campaign is considered active for the 10 days following its start-date. This method gets the
   * configurable number we consider a campaign is active since its start date.
   *
   * @return list of active campaigns
   */
  @Query(value = "SELECT *, MAX(bid)" +
      " FROM campaigns c INNER JOIN campaign_categories cc ON c.id=cc.campaign_id"
      + " WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(c.start_date)) <= 10"
      + " GROUP BY c.id", nativeQuery = true)
  CampaignAll getActiveCampaignsWithHighestBid();
}

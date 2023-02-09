package com.mabaya.advesrtise.repository.campaign;

import com.mabaya.advesrtise.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepository extends JpaRepository<Campaign, Long> {


  /**
   * Campaign is considered active for the 10 days following its start-date. This method gets the
   * configurable number we consider a campaign is active since its start date.
   *
   * @return list of active campaigns
   */
  @Query(value = "SELECT MAX(c.bid)" +
      " FROM campaigns as c INNER JOIN campaign_categories as cc on c.id= cc.campaign_id"
      + " WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(c.start_date)) <= :activeRangeDays "
      + " AND JSON_CONTAINS(cc.categories, :category)", nativeQuery = true)
  Campaign getActiveCampaignsWithHighestBid(String category, int activeRangeDays);

  /**
   * Campaign is considered active for the 10 days following its start-date. This method gets the
   * configurable number we consider a campaign is active since its start date.
   *
   * @return list of active campaigns
   */
  @Query(value = "SELECT MAX(c.bid)" +
      " FROM campaigns as c INNER JOIN campaign_categories as cc on c.id= cc.campaign_id"
      + " WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(c.start_date)) <= :activeRangeDays", nativeQuery = true)
  Campaign getActiveCampaignsWithHighestBid(int activeRangeDays);
}

package com.mabaya.advesrtise.repository.campaign;

import com.mabaya.advesrtise.model.Campaign;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepository extends JpaRepository<Campaign, Long> {

  //TODO replace SELECT * with specific columns

  /**
   * Campaign is considered active for the 10 days following its start-date.
   * This method gets the configurable number we consider a campaign is active since its start date.
   * @return list of active campaigns
   */
  @Query(value = "SELECT * " + "FROM campaigns "
      + "WHERE DATEDIFF(CURDATE(), FROM_UNIXTIME(start_date)) <= :activeRangeDays ", nativeQuery = true)
  List<Campaign> getActiveCampaigns(int activeRangeDays);
}

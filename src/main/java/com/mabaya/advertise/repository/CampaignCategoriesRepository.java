package com.mabaya.advertise.repository;

import com.mabaya.advertise.model.CampaignCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignCategoriesRepository extends JpaRepository<CampaignCategories, Integer> {


}

package com.mabaya.advertise.repository;

import com.mabaya.advertise.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepository extends JpaRepository<Campaign, Integer> {


}

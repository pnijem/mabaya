package com.mabaya.advesrtise.repository.campaign;

import com.mabaya.advesrtise.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRespository extends JpaRepository<Campaign, Integer> {

}

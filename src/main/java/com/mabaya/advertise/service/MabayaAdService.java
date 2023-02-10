package com.mabaya.advertise.service;

import com.mabaya.advertise.dto.CampaignRequestBody;
import com.mabaya.advertise.model.CampaignAll;
import com.mabaya.advertise.model.Product;

public interface MabayaAdService {

  CampaignAll createCampaign(CampaignRequestBody campaignInfo);

  Product getActiveProductWithHighestBid(String category);

}

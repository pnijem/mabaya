package com.mabaya.advesrtise.service;

import com.mabaya.advesrtise.dto.CampaignRequestBody;
import com.mabaya.advesrtise.model.Campaign;
import com.mabaya.advesrtise.model.Product;

public interface MabayaAdService {

  Campaign createCampaign(CampaignRequestBody campaignInfo);

  Product getActiveProductWithHighestBid(String category);

}

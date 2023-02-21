package com.mabaya.advertise.service;

import com.mabaya.advertise.dto.CampaignRequestBody;
import com.mabaya.advertise.dto.CampaignResponse;
import com.mabaya.advertise.dto.ProductResponse;

public interface MabayaAdService {

  CampaignResponse createCampaign(CampaignRequestBody campaignInfo);

  ProductResponse getActiveProductWithHighestBid(String category);

}

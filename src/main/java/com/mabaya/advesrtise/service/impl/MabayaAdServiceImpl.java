package com.mabaya.advesrtise.service.impl;

import com.mabaya.advesrtise.dto.CampaignRequestBody;
import com.mabaya.advesrtise.model.Campaign;
import com.mabaya.advesrtise.model.Product;
import com.mabaya.advesrtise.repository.ProductsRepository;
import com.mabaya.advesrtise.repository.campaign.CampaignsRepository;
import com.mabaya.advesrtise.service.MabayaAdService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
public class MabayaAdServiceImpl implements MabayaAdService {

  @Value("${mabaya.active-range-days}")
  private int activeRangeDays;

  private final CampaignsRepository campaignsRepository;

  private final ProductsRepository productsRepository;

  @Override
  public Campaign createCampaign(CampaignRequestBody campaignInfo) {
    List<Product> products = productsRepository.findAllById(campaignInfo.getProducts());
    Campaign campaign = Campaign.builder().name(campaignInfo.getName())
        .startDate(campaignInfo.getStartDate()).products(campaignInfo.getProducts())
        .categories(products.stream().map(Product::getCategory).collect(Collectors.toList()))
        .build();
    return campaignsRepository.save(campaign);
  }

  @Override
  public Product getActiveProductWithHighestBid(String category) {

    Campaign activeCampaignsByCategory = campaignsRepository.getActiveCampaignsWithHighestBid(
        category, activeRangeDays);

    List<Long> productIds =
        activeCampaignsByCategory != null ? activeCampaignsByCategory.getProducts()
            : Collections.emptyList();
    //If there are no promoted product for the matching category
    if (productIds.isEmpty()) {
      //Fetch the active campaign with the highest bid regardless of the category
      activeCampaignsByCategory = campaignsRepository.getActiveCampaignsWithHighestBid(
          activeRangeDays);
      productIds = activeCampaignsByCategory != null ? activeCampaignsByCategory.getProducts()
          : Collections.emptyList();
    }
    if (!productIds.isEmpty()) {
      return productsRepository.findById(productIds.get(0)).orElse(null);
    }
    return null;
  }

}

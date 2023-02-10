package com.mabaya.advertise.service.impl;

import com.mabaya.advertise.dto.CampaignRequestBody;
import com.mabaya.advertise.model.Campaign;
import com.mabaya.advertise.model.CampaignAll;
import com.mabaya.advertise.model.CampaignCategories;
import com.mabaya.advertise.model.Product;
import com.mabaya.advertise.repository.CampaignCategoriesRepository;
import com.mabaya.advertise.repository.CampaignsAllRepository;
import com.mabaya.advertise.repository.CampaignsRepository;
import com.mabaya.advertise.repository.ProductsRepository;
import com.mabaya.advertise.service.MabayaAdService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor

@Service
public class MabayaAdServiceImpl implements MabayaAdService {


  private final CampaignsRepository campaignsRepository;
  private final CampaignsAllRepository campaignsAllRepository;
  private final ProductsRepository productsRepository;
  private final CampaignCategoriesRepository campaignCategoriesRepository;


  @Override
  @Transactional(rollbackFor = Exception.class)
  public CampaignAll createCampaign(CampaignRequestBody campaignInfo) {
    List<Product> products = productsRepository.findAllById(campaignInfo.getProducts());
    Campaign campaign = Campaign.builder().name(campaignInfo.getName())
        .startDate(campaignInfo.getStartDate()).products(campaignInfo.getProducts())
        .bid(campaignInfo.getBid()).build();
    campaign = campaignsRepository.save(campaign);

    CampaignCategories campaignCategories = CampaignCategories.builder()
        .campaignId(campaign.getId())
        .categories(products.stream().map(Product::getCategory).collect(Collectors.toSet()))
        .build();
    campaignCategoriesRepository.save(campaignCategories);

    return CampaignAll.builder()
        .categories(campaignCategories.getCategories())
        .bid(campaign.getBid())
        .startDate(campaign.getStartDate())
        .products(campaign.getProducts())
        .id(campaign.getId())
        .name(campaign.getName())
        .build();


  }

  @Override
  public Product getActiveProductWithHighestBid(String category) {

    CampaignAll activeCampaignsByCategory = campaignsAllRepository.getActiveCampaignsWithHighestBid(category);

    List<Integer> productIds =
        activeCampaignsByCategory != null ? activeCampaignsByCategory.getProducts()
            : Collections.emptyList();
    //If there are no promoted product for the matching category
    if (productIds.isEmpty()) {
      //Fetch the active campaign with the highest bid regardless of the category
      activeCampaignsByCategory = campaignsAllRepository.getActiveCampaignsWithHighestBid();
      productIds = activeCampaignsByCategory != null ? activeCampaignsByCategory.getProducts()
          : Collections.emptyList();
    }
    if (!productIds.isEmpty()) {
      return productsRepository.findById(productIds.get(0)).orElse(null);
    }
    return null;
  }

}

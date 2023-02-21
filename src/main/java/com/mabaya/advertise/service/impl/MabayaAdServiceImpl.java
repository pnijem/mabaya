package com.mabaya.advertise.service.impl;

import com.mabaya.advertise.dto.CampaignRequestBody;
import com.mabaya.advertise.model.Campaign;
import com.mabaya.advertise.model.CampaignAll;
import com.mabaya.advertise.model.CampaignCategories;
import com.mabaya.advertise.model.Product;
import com.mabaya.advertise.repository.CampaignCategoriesRepository;
import com.mabaya.advertise.repository.CampaignsRepository;
import com.mabaya.advertise.repository.ProductsRepository;
import com.mabaya.advertise.service.MabayaAdService;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class MabayaAdServiceImpl implements MabayaAdService {


  private final CampaignsRepository campaignsRepository;
  private final ProductsRepository productsRepository;
  private final CampaignCategoriesRepository campaignCategoriesRepository;

  //In case one of the save() operations fail, rollback the other
  @Transactional(rollbackFor = Exception.class)
  @Override
  public CampaignAll createCampaign(CampaignRequestBody campaignInfo) {

    log.info("Saving new campaign to DB...");
    Campaign campaign = Campaign.builder().name(campaignInfo.getName())
        .startDate(campaignInfo.getStartDate()).products(campaignInfo.getProducts())
        .bid(campaignInfo.getBid()).build();

    campaign = campaignsRepository.save(campaign);
    log.info("Saved new campaign:{}", campaignInfo);

    log.info("Fetch all Products by ID");
    log.debug("Product IDs:{}", campaignInfo.getProducts());
    List<Product> products = productsRepository.findAllById(campaignInfo.getProducts());
    log.info("Fetched {} products from DB", products.size());
    Set<String> categories = products.stream().map(Product::getCategory)
        .collect(Collectors.toSet());
    log.debug("Product Categories:{}", categories);

    log.info("Saving new campaign categories mapping to DB...");
    CampaignCategories campaignCategories = CampaignCategories.builder()
        .campaign(campaign).categories(categories).build();
    campaignCategories = campaignCategoriesRepository.save(campaignCategories);
    log.info("Saved new mapping: {}", campaignCategories);

    return CampaignAll.builder().categories(campaignCategories.getCategories())
        .bid(campaign.getBid()).startDate(campaign.getStartDate()).products(campaign.getProducts())
        .id(campaign.getId()).name(campaign.getName()).build();


  }

  @Override
  public Product getActiveProductWithHighestBid(String category) {
    log.info("Fetch active Campaign with highest bid according to input category: {}", category);
    Campaign activeCampaignsByCategory = campaignsRepository.getActiveCampaignsWithHighestBid(
        category);

    List<Integer> productIds =
        activeCampaignsByCategory != null ? activeCampaignsByCategory.getProducts()
            : Collections.emptyList();
    //If there are no promoted product for the matching category
    if (productIds.isEmpty()) {
      log.info("Couldn't find Campaign with products of the required category");
      //Fetch the active campaign with the highest bid regardless of the category
      activeCampaignsByCategory = campaignsRepository.getActiveCampaignsWithHighestBid();
      productIds = activeCampaignsByCategory != null ? activeCampaignsByCategory.getProducts()
          : Collections.emptyList();
    }
    if (!productIds.isEmpty()) {
      return productsRepository.findById(productIds.get(0)).orElse(null);
    } else {
      log.warn("Not finding any active campaign");
    }
    return null;
  }

}

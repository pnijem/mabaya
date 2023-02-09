package com.mabaya.advesrtise.service.impl;

import com.mabaya.advesrtise.dto.CampaignRequestBody;
import com.mabaya.advesrtise.model.Campaign;
import com.mabaya.advesrtise.model.Product;
import com.mabaya.advesrtise.repository.ProductsRepository;
import com.mabaya.advesrtise.repository.campaign.CampaignsRepository;
import com.mabaya.advesrtise.service.MabayaAdService;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    Campaign campaign = Campaign.builder().name(campaignInfo.getName())
        .startDate(campaignInfo.getStartDate()).products(campaignInfo.getProducts()).build();
    return campaignsRepository.save(campaign);
  }

  @Override
  public Product getActiveProductWithHighestBid(String category) {

    List<Campaign> activeCampaigns = campaignsRepository.getActiveCampaigns(activeRangeDays);

    Set<Long> activeProductsIds = new HashSet<>();
    activeCampaigns.forEach(
        activeCampaign -> activeProductsIds.addAll(activeCampaign.getProducts()));

    List<Product> activeProducts = productsRepository.findAllById(activeProductsIds);
    List<Product> activeProductsByCategory = activeProducts.stream()
        .filter(product -> category.equals(product.getCategory())).collect(Collectors.toList());

    //If there are no promoted product for the matching category
    if (activeProductsByCategory.isEmpty()) {
      activeProductsByCategory = activeProducts;
    }
    Set<Long> relevantCampaigns = new HashSet<>();
    activeProductsByCategory.stream().map(Product::getCampaigns).forEach(relevantCampaigns::addAll);

    List<Long> activeCampaignIds = activeCampaigns.stream().map(Campaign::getId)
        .collect(Collectors.toList());
    Set<Long> activeRelevantCampaigns = activeCampaignIds.stream()
        .filter(relevantCampaigns::contains).collect(Collectors.toSet());

    List<Campaign> activeCampaignsByCategory = campaignsRepository.findAllById(
        activeRelevantCampaigns);

    Optional<Campaign> highestBidCampaignOptional = activeCampaignsByCategory.stream()
        .max(Comparator.comparing(Campaign::getBid));

    if (highestBidCampaignOptional.isPresent()) {
      Campaign highestBidCampaign = highestBidCampaignOptional.get();
      return activeProductsByCategory.stream()
          .filter(product -> highestBidCampaign.getProducts().contains(product.getId())).findFirst()
          .orElse(null);
    }
    return null;

  }

}

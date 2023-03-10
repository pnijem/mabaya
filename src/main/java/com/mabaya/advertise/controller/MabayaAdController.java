package com.mabaya.advertise.controller;

import com.mabaya.advertise.dto.CampaignRequestBody;
import com.mabaya.advertise.dto.CampaignResponse;
import com.mabaya.advertise.dto.ProductResponse;
import com.mabaya.advertise.service.MabayaAdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1")
public class MabayaAdController {

  private final MabayaAdService mabayaAdService;


  @Operation(summary = "Endpoint for creating a new Campaign")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "A Campaign successfully created", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)})
  })

  @PostMapping(value = "/campaign", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CampaignResponse> createCampaign(
      @Valid @RequestBody CampaignRequestBody requestBody) {
    log.info("Start createCampaign()");
    CampaignResponse campaign = mabayaAdService.createCampaign(requestBody);
    ResponseEntity<CampaignResponse> response = ResponseEntity.status(HttpStatus.CREATED)
        .body(campaign);
    log.info("Finish createCampaign()");
    return response;
  }

  @Operation(summary =
      "Endpoint for returning a single promoted product, the one with the highest bid,\n"
          + "belonging to active campaign/s from the specified category")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "A Product successfully returned", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
      @ApiResponse(responseCode = "404", description = "No Product has been found", content = {
          @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)}),
      @ApiResponse(responseCode = "400", description = "Bad request", content = {
          @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)})
  })

  @GetMapping(value = "/ad/product")
  public ResponseEntity<ProductResponse> getAdvertisedProduct(
      @RequestParam(name = "category") @NotBlank String category) {
    log.info("Start getAdvertisedProduct()");
    ProductResponse product = mabayaAdService.getActiveProductWithHighestBid(category);
    if (product == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    ResponseEntity<ProductResponse> response = ResponseEntity.status(HttpStatus.OK).body(product);
    log.info("Finish getAdvertisedProduct()");
    return response;
  }
}

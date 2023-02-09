package com.mabaya.advesrtise.controller;

import com.mabaya.advesrtise.dto.CampaignRequestBody;
import com.mabaya.advesrtise.dto.CampaignResponseBody;
import com.mabaya.advesrtise.dto.ProductResponseBody;
import com.mabaya.advesrtise.service.MabayaAdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//@Tag(name = "Mabaya Advertising API")
@RequestMapping("/api/v1")
public class MabayaAdController {

  private final MabayaAdService mabayaAdService;


  @Operation(summary = "Endpoint for creating a new Campaign")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "A campaign successfully created", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})})

  @PostMapping(value = "/campaign", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CampaignResponseBody> createCampaign(
      @Valid @RequestBody CampaignRequestBody requestBody) {

    return ResponseEntity.status(HttpStatus.CREATED).body(CampaignResponseBody.builder().build());

  }

  @GetMapping(value = "/ad/product", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<ProductResponseBody> getAdvertisedProduct(
      @RequestParam(name = "category") String category) {
    return ResponseEntity.status(HttpStatus.OK).body(ProductResponseBody.builder().build());
  }
}

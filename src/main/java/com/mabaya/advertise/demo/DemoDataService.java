package com.mabaya.advertise.demo;

import com.mabaya.advertise.model.Product;
import com.mabaya.advertise.repository.ProductsRepository;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DemoDataService implements CommandLineRunner {

  private static final List<String> CATEGORIES = List.of("clothing", "electronics", "sports",
      "food", "cosmetics");

  private final static int DEMO_PRODUCTS_NUM = 100;
  private final ProductsRepository productsRepository;
  private final Random random;

  @Override
  public void run(String... args) {

    if (productsRepository.count() != DEMO_PRODUCTS_NUM) {
      double price = 99.9;
      Product product;
      for (int i = 1; i <= DEMO_PRODUCTS_NUM; i++) {
        product = Product.builder().price(price).title(generateRandomString())
            .serialNumber(UUID.randomUUID().toString()).category(getRandomCategory()).build();

        productsRepository.save(product);
      }
    }
  }

  private String getRandomCategory() {
    return CATEGORIES.get(random.nextInt(CATEGORIES.size()));
  }

  private String generateRandomString() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;

    return random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}

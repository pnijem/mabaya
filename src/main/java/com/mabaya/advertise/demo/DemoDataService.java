package com.mabaya.advertise.demo;

import com.mabaya.advertise.model.Product;
import com.mabaya.advertise.repository.ProductsRepository;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DemoDataService implements CommandLineRunner {

  private static final List<String> categories = List.of("clothing", "electronics", "sports",
      "food", "cosmetics");
  private final ProductsRepository productsRepository;

  private final Random random;

  @Override
  public void run(String... args) {

    if (productsRepository.count() != 100) {
      double price = 99.9;
      Product product;
      for (int i = 1; i <= 100; i++) {
        product = Product.builder()
            .price(price)
            .title(generateRandomString())
            .serialNumber(generateRandomString())
            .category(getRandomCategory()).build();

        productsRepository.save(product);
      }
    }
  }

  private String getRandomCategory() {
    return categories.get(random.nextInt(categories.size()));
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

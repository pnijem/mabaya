package com.mabaya.advertise.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MabayaConfiguration {

  @Bean
  public Random random() {
    return new Random();
  }

}

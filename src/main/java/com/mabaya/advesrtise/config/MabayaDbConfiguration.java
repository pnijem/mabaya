package com.mabaya.advesrtise.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "mabayaDbEntityManagerFactory",
    transactionManagerRef = "mabayaDbTransactionManager",
    basePackages = {"com.mabaya.advesrtise.repository.campaign"}
)
public class MabayaDbConfiguration {

  @Bean("mabayaDbHikariConfig")
  @ConfigurationProperties(prefix = "mabaya.db.datasource")
  public HikariConfig hikariConfig() {
    return new HikariConfig();
  }

  @Bean(name = "mabayaDbDataSource")
  public DataSource dataSource(@Qualifier("mabayaDbHikariConfig") HikariConfig hikariConfig) {
    return new HikariDataSource(hikariConfig);
  }

  @Bean(name = "mabayaDbEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean
  mabayaDbEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("mabayaDbDataSource") DataSource dataSource
  ) {
    return
        builder
            .dataSource(dataSource)
            .packages("com.mabaya.advertise.model.campaign")
            .persistenceUnit("campaign")
            .build();
  }

  @Primary
  @Bean(name = "mabayaDbTransactionManager")
  public PlatformTransactionManager bigDbTransactionManager(
      @Qualifier("mabayaDbEntityManagerFactory") EntityManagerFactory mabayaDbEntityManagerFactory) {
    return new JpaTransactionManager(mabayaDbEntityManagerFactory);
  }

}

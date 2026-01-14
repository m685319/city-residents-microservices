package com.example.cityresidents;

import com.example.cityresidents.config.KafkaTopicsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(KafkaTopicsProperties.class)
public class CityResidentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CityResidentsApplication.class, args);
    }
}

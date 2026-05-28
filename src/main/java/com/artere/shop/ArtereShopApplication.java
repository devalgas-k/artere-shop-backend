package com.artere.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@SpringBootApplication
@Modulithic(sharedModules = {"shared"})
public class ArtereShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtereShopApplication.class, args);
    }
}

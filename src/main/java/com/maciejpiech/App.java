package com.maciejpiech;

import com.maciejpiech.productcatalog.MapProductStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.maciejpiech.creditcard.NameProvider;
import com.maciejpiech.productcatalog.ProductCatalog;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    @Bean
    NameProvider createNameProvider() {
        return new NameProvider();
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new MapProductStorage());
        String productId1 = productCatalog.addProduct("001", "Black Tea");
        productCatalog.assignImage(productId1, "https://as2.ftcdn.net/v2/jpg/03/21/99/89/1000_F_321998935_OEmaxE5Opa7cESmIClPtsFYeawPtzzvE.jpg");
        productCatalog.assignPrice(productId1, BigDecimal.TEN);
        productCatalog.publish(productId1);

        String productId2 = productCatalog.addProduct("002", "Green Tea");
        productCatalog.assignImage(productId2, "https://as2.ftcdn.net/v2/jpg/00/80/80/01/1000_F_80800153_mZhPpHuRLOICxFgmRiH7GOvELJZ2B4Lo.jpg");
        productCatalog.assignPrice(productId2, BigDecimal.valueOf(20.20));
        productCatalog.publish(productId2);

        return productCatalog;
    }
}